# architect.py
from .base_agent import BaseAgent
import json
from src.utils.utils import call_LLM,load_config

class Designer(BaseAgent):
    def __init__(self,logging=None):
        super().__init__("Designer", 
                         "The Designer is responsible for transforming high-level software requirements into precise and executable system designs. This role interprets functional and non-functional requirements, identifies core domain concepts, and models them into Class Diagrams and Sequence Diagrams that accurately represent system structure and behavior. The Designer ensures that the generated UML artifacts maintain consistency with requirements, follow software engineering best practices, and provide a solid foundation for implementation."
                            )
        self.prompt_path = "./agent/src/prompts/designer/designer.txt"
        self.update_design_prompt_path = "./agent/src/prompts/designer/update_designer.txt"
        self.recommendation_prompt = "./agent/src/prompts/designer/recommendation.txt"
        self.logging=logging
        self.memory={}

    def analysis(self,requirements) -> any:
        with open(self.recommendation_prompt, 'r') as file:
            prompt_template = file.read()
            prompt = prompt_template.replace("{Use Case}", str(requirements))
            self.logging.info("Analysis Prompt:\n"+f"{prompt}")
        api_key, api_base, model = load_config()
        output = call_LLM(prompt, model, api_key, api_base)
        self.logging.info("Analysis LLM Output:\n"+f"{output}")
        self.memory['analysis_report'] = output
        return output
    
    def act(self, requirements,architect_report) -> any:
        """Design system architecture based on requirements."""
        with open(self.prompt_path, 'r') as file:
            prompt_template = file.read()
            prompt = prompt_template.replace("{UC}", str(requirements))
            prompt = prompt.replace("{architect_report}",str(architect_report))
            self.logging.info("Architect Prompt:\n"+f"{prompt}")
        api_key, api_base, model = load_config()
        output = call_LLM(prompt, model, api_key, api_base)
        self.logging.info("Architect LLM Output:\n"+f"{output}")
        self.memory['initial_design'] = output
        self.memory['updated_design'] = output
        return output
    
    def update_design(self, feedback) -> any:
        with open(self.update_design_prompt_path, 'r') as file:
            prompt_template = file.read()
        feedback=str(feedback)
        design=self.memory.get('updated_design','')
        prompt=prompt_template.replace("{diagram}",design)
        prompt=prompt.replace("{audit_report}",feedback)
        self.logging.info("Update Design Prompt:\n"+f"{prompt}")
        api_key, api_base, model = load_config()
        output = call_LLM(prompt, model, api_key, api_base)
        self.logging.info("Update Design LLM Output:\n"+f"{output}")
        self.memory['updated_design'] = output
        return output
    
    def update_graph(self,class_diagram_graph,sequence_diagram_graph):
        self.memory['class_diagram_graph']=class_diagram_graph
        self.memory['sequence_diagram_graph']=sequence_diagram_graph
        
