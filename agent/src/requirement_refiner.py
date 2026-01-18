# requirement_analyst.py
import os
from .base_agent import BaseAgent
from src.utils.usecaseparser import UseCaseParser
from src.utils.utils import call_LLM, load_prompt, generate_class_diagram_graph, generate_sequence_diagram_graph, generate_requirement_graph,load_config
class RequirementRefiner(BaseAgent):
    def __init__(self,logging=None):
        super().__init__("RequirementRefiner", "Refine requirements")
        self.prompt_path = "./agent/src/prompts/refiner/req_refine_with_grammar.txt"
        self.prompt_path_check = "./agent/src/prompts/refiner/update_req_with_grammar.txt"
        self.logging=logging
        self.memory={}

    def act(self,initial_requirements):
        self.logging.info("Starting requirement refinement process.")
        with open(self.prompt_path, "r", encoding="utf-8") as f:
            prompt_template = f.read()
        prompt = prompt_template.replace("{OriginalUseCase}", initial_requirements)
        self.logging.debug(f"Refinement Prompt: {prompt}")
        api_key, api_base, model_name = load_config()
        refined_requirements = call_LLM(prompt, api_key=api_key, api_url=api_base, model=model_name)
        if refined_requirements.startswith("```"):
            refined_requirements = refined_requirements.strip("```").strip()
        if refined_requirements.endswith("```"):
            refined_requirements = refined_requirements.rstrip("```").strip()
        self.logging.info("Completed requirement refinement.")
        self.logging.debug(f"Refined Requirements: {refined_requirements}")
        return refined_requirements

    def check_requirements(self,requirements):
        self.logging.info("Starting requirement checking process.")
        usecase_parser = UseCaseParser(requirements)
        feedback=usecase_parser.parse()
        self.logging.info("Completed requirement checking process.")
        self.logging.debug(f"Requirement Check Feedback: {feedback}")
        return str(feedback)
        
    def react_requirements(self,requirements,feedback,refined_requirements):
        self.logging.info("Starting requirement reaction process.")
        with open(self.prompt_path_check, "r", encoding="utf-8") as f:
            prompt_template = f.read()
        prompt = prompt_template.replace("{OriginalUseCase}", requirements).replace("{FormalCheckResult}", feedback).replace("{ReconstructedUseCase}", refined_requirements)
        self.logging.debug(f"Reaction Prompt: {prompt}")
        api_key, api_base, model_name = load_config()
        updated_requirements = call_LLM(prompt, api_key=api_key, api_url=api_base, model=model_name)
        self.logging.info("Completed requirement reaction.")
        self.logging.debug(f"Updated Requirements: {updated_requirements}")
        return updated_requirements
    
