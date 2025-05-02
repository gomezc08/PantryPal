import openai
from dotenv import load_dotenv, find_dotenv
import os
import time
import requests
import logging
import json
from datetime import datetime
import streamlit as st

load_dotenv()
client = openai.OpenAI()
model = "gpt-3.5-turbo-16k"

class Agent:
    thread_id = None
    assistant_id = None
    
    def __init__(self, model : str):
        self.client = client
        self.model = model
        self.assistant = None
        self.thread = None
        self.run = None
        self.summary = None
    
        # retrieve existing assistant and threads if not none.
        if Agent.assistant_id:
            self.assistant = self.client.beta.assistants.retrieve(
                assistant_id=Agent.assistant_id
            )
        if Agent.thread_id:
            self.thread = self.client.beta.threads.retrieve(
                thread_id = Agent.thread_id
            )
        
    def create_assistant(self, name, instructions, tools):
        # create assistant if not exists.
        if not self.assistant:
            assistant_object = self.client.beta.assistants.create(
                model=self.model,
                instructions=instructions,
                name=name,
                tools=tools
            )
            # store variables of assistant.
            Agent.assistant_id = assistant_object.id
            self.assistant = assistant_object
            print(f"Assistant created: {self.assistant.id}")
    
    def create_thread(self):
        # create thread if not exists.
        if not self.thread:
            thread_object = self.client.beta.threads.create()

            # store variables of thread.
            self.thread = thread_object
            self.thread_id = thread_object.id
            print(f"Thread created: {self.thread_id}")

    def add_message_to_thread(self, role, message):
        if self.thread:
            self.client.beta.threads.messages.create(
                thread_id=self.thread_id,
                role=role,
                content=message
            )
    
    def run_assistant(self, additional_instructions):
        if self.assistant and self.thread:
            run_object = self.client.beta.threads.runs.create(
                thread_id=self.thread_id,
                assistant_id=self.assistant_id,
                instructions=additional_instructions
            )

    
    def get_thread_message(self):
        if self.thread:
            messages = self.client.beta.threads.messages.list(
                thread_id=self.thread_id
            )
            summary = []

            last_message = messages.data[0]
            role = last_message.role
            chatgpt_response = last_message.content[0].text.value

            # store summary.
            summary.append(chatgpt_response)
            self.summary = "\n".join(summary)

            print(f"Summary: {role.capitalize(): {chatgpt_response}}")
    
    """def plugin(self, required_action):
        if not self.run:
            return 
        
        tool_output = []
        
        for action in required_action["tool_calls"]:
            func_name = action["function"]["name"]
            arguments = json.loads(action["function"]["arguments"])

            # get news.
            if func_name == "get_news":
                output = get_news(topic=arguments["topic"])
                print(f"Output: {output}")
                final_str = ""
                for out in output:
                    final_str += "".join(out)
                
                tool_output.append({"tool_call_id" : action["id"], 
                                    "output" : final_str})
                print("Submitting tool output to assistant...")
            
            else:
                raise ValueError(f"Tool {func_name} not found.")
        
        # submit tool output.
        self.client.beta.threads.runs.submit_tool_outputs(
            run_id=self.run.id,
            thread_id=self.thread_id,
            tool_outputs=tool_output
        )"""

    def get_summary(self):
        return self.summary 

    def wait_for_run_completion(self):
        if self.run and self.thread:
            while True:
                time.sleep(5)
                run_status = self.client.beta.threads.runs.retrieve(
                    run_id=self.run.id,
                    thread_id=self.thread_id
                )
                print(f"Run status: {run_status.model_dump_json(indent=4)}")

                if run_status.status == "completed":
                    self.get_thread_message()
                    break
                elif run_status.status == "requires_action":
                    print("Calling plugin...")
                    #self.plugin(run_status.required_action.submit_tool_outputs.model_dump())
    
    def run_steps(self):
        run_steps = self.client.beta.threads.runs.steps.list(
            thread_id=self.thread_id,
            run_id=self.run.id
        )
        print(f"Run steps: {run_steps}")


def main():
    manager = Agent(model=model)

    # creatin streamlit interface.
    st.title("News Summarizer")

    with st.form(key="user_input_form"):
        instructions = st.text_input("Enter topic:")
        submit_button = st.form_submit_button(label="Run Assistant")

    if submit_button:
        manager.create_assistant(name="News Summarizer", 
                                 instructions="You are a personal trainer. You specialize in reccomending healthy food items and recipes based on what the user has in their pantry.", 
                                 tools=[{
                                    "type" : "function",
                                    "function" : {
                                        "name" : "get_news",
                                        "description" : "Get news based on the topic provided by the user.",
                                        
                                    }
                                 }])
        manager.create_thread()
        manager.add_message_to_thread(role="user", message=instructions)
        manager.run_assistant(additional_instructions=instructions)
        manager.wait_for_run_completion()
        summary = manager.get_summary()
        st.write(summary)

if __name__ == "__main__":
    main()