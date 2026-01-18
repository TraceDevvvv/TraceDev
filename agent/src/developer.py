# architect.py
from .base_agent import BaseAgent
import json
from src.utils.utils import call_LLM,load_config,extract_codes

class Developer(BaseAgent):
    def __init__(self,logging=None):
        super().__init__("Developer", 
                         "The Developer is responsible for implementing the software design into functional code. This role translates UML diagrams and design specifications into clean, efficient, and maintainable code while adhering to coding standards and best practices. The Developer collaborates closely with the Architect and Quality Checker to ensure that the implementation aligns with the intended design and meets quality requirements."
                            )
        self.prompt_path = "./agent/src/prompts/developer/developer.txt"
        self.update_implementation_prompt_path = "./agent/src/prompts/developer/update_code.txt"
        self.logging=logging
        self.memory={}

    def act(self, design_model) -> any:
        """Implement system based on design model."""
        with open(self.prompt_path, 'r') as file:
            prompt_template = file.read()
            prompt = prompt_template.format(text=design_model)
            self.logging.info("Developer Prompt:\n"+f"{prompt}")
        api_key, api_base, model = load_config()
        output = call_LLM(prompt, model, api_key, api_base)
        # output = extract_codes(output)
        output = extract_codes(output)
        self.logging.info("Developer LLM Output:\n")
        self.logging.info(json.dumps(output, indent=4, ensure_ascii=False))
        self.memory['implementation_code'] = output
        self.memory['updated_implementation'] = output
        return output
    
    def update_implementation(self, feedback) -> any:
        with open(self.update_implementation_prompt_path, 'r') as file:
            prompt_template = file.read()
        implementation_code=self.memory.get('updated_implementation',[])
        prompt=prompt_template.replace("<CODES>",json.dumps(implementation_code))
        prompt=prompt.replace("<CLASS_MISSING_JSON>",json.dumps(feedback["class_diagram"]))
        prompt=prompt.replace("<SEQUENCE_MISSING_JSON>",json.dumps(feedback["sequence_diagram"]))
        self.logging.info("Update Implementation Prompt:\n"+f"{prompt}")
        api_key, api_base, model = load_config()
        output = call_LLM(prompt, model, api_key, api_base)
        output = extract_codes(output)
        # implementation_code = json.dumps(implementation_code, indent=2, ensure_ascii=False)
      
        for file in output:
            filename = file['filename']
            content = file['content']
        
            for impl_file in implementation_code:
                if impl_file['filename'] == filename:
                    impl_file['content'] = content
                    break
         
            else:
                implementation_code.append({
                    'filename': filename,
                    'content': content
                })
        # output = json.dumps(implementation_code, indent=2, ensure_ascii=False)
        output = implementation_code
        self.logging.info("Update Implementation LLM Output:\n")
        self.logging.info(json.dumps(output, indent=4, ensure_ascii=False))
        self.memory['implementation_code'] = output
        return output

    def act1(self, design_model) -> any:
        """Implement system based on design model."""
        with open("./agent/src/prompts/developer/developer_wo_desiner.txt", 'r') as file:
            prompt_template = file.read()
            prompt = prompt_template.format(text=design_model)
            self.logging.info("Developer Prompt:\n"+f"{prompt}")
        api_key, api_base, model = load_config()
        output = call_LLM(prompt, model, api_key, api_base)
        # output = extract_codes(output)
        output = extract_codes(output)
        self.logging.info("Developer LLM Output:\n")
        self.logging.info(json.dumps(output, indent=4, ensure_ascii=False))
        self.memory['implementation_code'] = output
        self.memory['updated_implementation'] = output
        return output
    
    def update_code_with_tester(self, feedback) -> any:
        with open("./agent/src/prompts/developer/update_code_tester.txt", 'r') as file:
            prompt_template = file.read()
        implementation_code=self.memory.get('updated_implementation',[])
        prompt=prompt_template.replace("<ORIGINAL_CODE>",json.dumps(implementation_code))
        prompt=prompt.replace("<TESTCASE_FEEDBACK>",feedback)
        self.logging.info("Update Implementation Prompt:\n"+f"{prompt}")
        api_key, api_base, model = load_config()
        output = call_LLM(prompt, model, api_key, api_base)
        output = extract_codes(output)
        # implementation_code = json.dumps(implementation_code, indent=2, ensure_ascii=False)
  
        for file in output:
            filename = file['filename']
            content = file['content']
       
            for impl_file in implementation_code:
                if impl_file['filename'] == filename:
                    impl_file['content'] = content
                    break
       
            else:
                implementation_code.append({
                    'filename': filename,
                    'content': content
                })
        # output = json.dumps(implementation_code, indent=2, ensure_ascii=False)
        output = implementation_code
        self.logging.info("Update Implementation LLM Output:\n")
        self.logging.info(json.dumps(output, indent=4, ensure_ascii=False))
        self.memory['updated_implementation'] = output
        return output