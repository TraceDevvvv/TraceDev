from src.designer import Designer
from src.validator import Validator
from src.developer import Developer
from src.tester import Tester
from src.requirement_refiner import RequirementRefiner
from src.utils.utils import construct_dir,from_req_to_json,generate_class_diagram_graph,generate_sequence_diagram_graph
import json
import os
import argparse
import logging
from src.utils.utils import extract_class_sequence_diagram
import datetime
import os


def main():
    parser = argparse.ArgumentParser()


    ############## GENERAL SETTINGS ###############
    parser.add_argument("--model", default="efficiency-deepseek-v3.2", help="Model type (e.g., gemini-2.5-flash, gpt-4)")
    parser.add_argument("--method", default="eTour", help="Method name (e.g., our_method, baseline)")
    parser.add_argument("--dataset", default="eTour", help="Dataset name")
    parser.add_argument("--root-output-dir", default="results", help="Root directory for outputs")
    parser.add_argument("--uc-case-dir", default="./data/eTour/req", help="The directory containing use case files")
    parser.add_argument("--start-uc-id", type=int, default=1, help="Start UC id")
    parser.add_argument("--end-uc-id", type=int, default=1, help="End UC id")
    parser.add_argument("--check-rounds", type=int, default=3, help="Number of quality check rounds")


    args = parser.parse_args()
    base_out = os.path.join(args.root_output_dir, args.model, args.method, args.dataset)

    # --- Requirement Refiner ---
    args.output_refined_req_dir = os.path.join(base_out, "refined_requirements")

    # --- Designer ---
    args.output_initial_design_dir = os.path.join(base_out, "initial_design")
    args.output_updated_design_dir = os.path.join(base_out, "updated_design")

    # --- Validator ---
    trace_base = os.path.join(base_out, "trace")
    args.output_trace_class_dir = os.path.join(trace_base, "class_diagram")
    args.output_trace_sequence_dir = os.path.join(trace_base, "sequence_diagram")
    args.miss_link_re_design_dir = os.path.join(base_out, "missing_link_redesign")
    args.output_design_quality_report_dir = os.path.join(base_out, "quality_report")
    args.output_class_json_dir = os.path.join(base_out, "class_diagram_json")
    args.output_sequence_json_dir = os.path.join(base_out, "sequence_diagram_json")

    # --- Developer ---
    args.output_base_dir = os.path.join(base_out, "gencode")
    args.trace_file_dir = os.path.join(base_out, "trace_code")
    args.output_trace_code_miss_links_dir = os.path.join(base_out, "trace_code_missing_links")

    # --- Token ---
    args.output_token_consumption = os.path.join(base_out, "token_consumption")
    construct_dir(args.output_token_consumption)

    for uc_id in range(args.start_uc_id,args.end_uc_id+1):
        token_path = "token_consumption_etour.json"
        token_dict = {"input_token": 0, "output_token": 0, "total_token": 0}
        with open(token_path, "w", encoding="utf-8") as f:
            json.dump(token_dict, f, ensure_ascii=False, indent=2)
      
        time= datetime.datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
        if not os.path.exists('log/eTour-ds'):
            os.makedirs('log/eTour-ds')
        logging.basicConfig(
            filename=f'log/eTour-ds/app_{time}_UC{uc_id}.log',
            level=logging.INFO,
            format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
            force=True
        )
        logging.info("This goes to file app.log")

        args.use_case_dir= "./data/eTour/req"
        try:
            use_case_path = f"{args.use_case_dir}/UC{uc_id}.txt"
            with open (use_case_path, 'r', encoding='utf-8') as file:
                use_case_content = file.read()
                logging.info(f"Read use case from {use_case_path}")
        except Exception as e:
            with open(use_case_path,'r', encoding='latin-1') as file:
                use_case_content = file.read()
                logging.warning(f"Encountered error reading {use_case_path} with default encoding: {e}. Read with latin-1 encoding instead.")
        
        ############# RequirementRefiner #############
        requirement_refiner= RequirementRefiner(logging=logging)
        refined_requirements= requirement_refiner.act(use_case_content)
        refined_req_path = f"{args.output_refined_req_dir}/initial_req/UC{uc_id}_refined_requirements.txt"
        construct_dir(refined_req_path)
        with open(refined_req_path, "w", encoding="utf-8") as f:
            f.write(refined_requirements)
        logging.info(f"Saved refined requirements to {refined_req_path}")
        feedback= requirement_refiner.check_requirements(refined_requirements)
        refined_req_feedback_path = f"{args.output_refined_req_dir}/feedback/UC{uc_id}_refined_requirements_feedback.txt"
        construct_dir(refined_req_feedback_path)
        with open(refined_req_feedback_path, "w", encoding="utf-8") as f:
            f.write(str(feedback))
        logging.info(f"Saved refined requirements feedback to {refined_req_feedback_path}")
        if "True" in feedback:
            updated_req_path = f"{args.output_refined_req_dir}/updated_req/UC{uc_id}_updated_requirements.txt"
            construct_dir(updated_req_path)
            with open(updated_req_path, "w", encoding="utf-8") as f:
                f.write(refined_requirements)
            updated_requirements = refined_requirements
        else:
            updated_requirements= requirement_refiner.react_requirements(use_case_content, feedback, refined_requirements)
            updated_req_path = f"{args.output_refined_req_dir}/updated_req/UC{uc_id}_updated_requirements.txt"
            construct_dir(updated_req_path)
            with open(updated_req_path, "w", encoding="utf-8") as f:
                f.write(updated_requirements)
            logging.info(f"Saved updated requirements to {updated_req_path}")
            feedback_1= requirement_refiner.check_requirements(updated_requirements)
            updated_req_feedback_path = f"{args.output_refined_req_dir}/feedback_updated/UC{uc_id}_updated_requirements_feedback.txt"
            construct_dir(updated_req_feedback_path)
            with open(updated_req_feedback_path, "w", encoding="utf-8") as f:
                f.write(feedback_1)
            logging.info(f"Saved updated requirements feedback to {updated_req_feedback_path}")

        use_case_json = from_req_to_json(updated_requirements)
        logging.info(f"Starting evaluation iteration {uc_id}")
        designer = Designer(logging=logging)

        # # # ############# Designer designs system #############
        designer_report = designer.analysis(use_case_json)
        designer_report_path = f"{args.output_initial_design_dir}/UC{uc_id}_designer_analysis.txt"
        construct_dir(designer_report_path)
        with open(designer_report_path, "w", encoding="utf-8") as f:
            f.write(designer_report)
        logging.info(f"Saved designer analysis report to {designer_report_path}")

        design_model=designer.act(use_case_json,designer_report)
        initial_design_path = f"{args.output_initial_design_dir}/UC{uc_id}_design.txt"
        construct_dir(initial_design_path)
        with open(initial_design_path, "w", encoding="utf-8") as f:
            f.write(design_model)

        # ############# Validator evaluates design #############
        validator = Validator(logging=logging)
       #############
        construct_flag_uml_rq = False
        for i in range(1,args.check_rounds+1):
            logging.info(f"Starting quality check iteration {i} for UC{uc_id}")
            design_model=designer.memory.get('updated_design','')
            res= extract_class_sequence_diagram(design_model)
            class_diagram=res['class_diagram']
            sequence_diagram=res['sequence_diagram']
            logging.info(f"Extracted class diagram and sequence diagram for UC{uc_id}")
            logging.info(f"Class Diagram:\n{class_diagram}")
            logging.info(f"Sequence Diagram:\n{sequence_diagram}")
            tracibility_info,trace_class,trace_sequence, class_json_graph, sequence_json_graph=validator.construct_tracibility(
                requirements=use_case_json,
                class_diagram=class_diagram,
                sequence_diagram=sequence_diagram
            )

            trace_class_path = f"{args.output_trace_class_dir}/UC{uc_id}_trace_class.json"
            construct_dir(trace_class_path)
            with open(trace_class_path, "w", encoding="utf-8") as f:
                json.dump(trace_class, f, ensure_ascii=False, indent=2)
            logging.info(f"Saved class diagram traceability info to {trace_class_path}")
            trace_sequence_path = f"{args.output_trace_sequence_dir}/UC{uc_id}_trace_sequence.json"
            construct_dir(trace_sequence_path)
            with open(trace_sequence_path, "w", encoding="utf-8") as f:
                json.dump(trace_sequence, f, ensure_ascii=False, indent=2)
            logging.info(f"Saved sequence diagram traceability info to {trace_sequence_path}")
            missing_link_redesign_path = f"{args.miss_link_re_design_dir}/UC{uc_id}_missing_link_redesign.json"
            construct_dir(missing_link_redesign_path)
            with open(missing_link_redesign_path, "w", encoding="utf-8") as f:
                json.dump(tracibility_info, f, ensure_ascii=False, indent=2)
            class_json_path = f"{args.output_class_json_dir}/UC{uc_id}_class_diagram.json"
            construct_dir(class_json_path)
            with open(class_json_path, "w", encoding="utf-8") as f:
                json.dump(class_json_graph, f, ensure_ascii=False, indent=2)
            logging.info(f"Saved class diagram json graph to {class_json_path}")
            sequence_json_path = f"{args.output_sequence_json_dir}/UC{uc_id}_sequence_diagram.json"
            construct_dir(sequence_json_path)  
            with open(sequence_json_path, "w", encoding="utf-8") as f:
                json.dump(sequence_json_graph, f, ensure_ascii=False, indent=2)
            logging.info(f"Saved sequence diagram json graph to {sequence_json_path}")
            report=validator.act(tracibility_info,use_case_json,design_model)
            design_quality_report_path = f"{args.output_design_quality_report_dir}/UC{uc_id}_quality_report.txt"
            construct_dir(design_quality_report_path)
            with open(design_quality_report_path, "w", encoding="utf-8") as f:
                f.write(report)
            logging.info(f"Saved design quality report to {design_quality_report_path}")
            designer.update_design(feedback=report)
            construct_flag_uml_rq = True
            if tracibility_info['class_diagram']==[] or tracibility_info['sequence_diagram']==[]:
                break
            
        ############# Developer implements system #############
        ############# #############

        developer=Developer(logging=logging)
        final_design=designer.memory.get('updated_design','')

        implementation_code=developer.act(final_design)
        updated_design_path = f"{args.output_updated_design_dir}/UC{uc_id}_updated_design.txt"
        construct_dir(updated_design_path)
        with open(updated_design_path, "w", encoding="utf-8") as f:
            f.write(final_design)
        logging.info(f"Saved updated design model to {updated_design_path}")
        
        for i in range(1,args.check_rounds+1):
            logging.info(f"Starting implementation update iteration {i} for UC{uc_id}")
            implementation_code=developer.memory.get('updated_implementation',[])
            feedback,code_tracibility=validator.construct_code_tracibility(
                implementation_code=implementation_code
            )
            trace_file_path = f"{args.trace_file_dir}/UC{uc_id}_code_traceability.json"
            if not os.path.exists(args.trace_file_dir):
                os.makedirs(args.trace_file_dir)
            with open(trace_file_path, "w", encoding="utf-8") as f:
                json.dump(code_tracibility, f, ensure_ascii=False, indent=2)
            logging.info(f"Saved code traceability info to {trace_file_path}")
            developer.update_implementation(feedback=feedback)
            missing_link_redesign_path = f"{args.output_trace_code_miss_links_dir}/UC{uc_id}_missing_link_redesign.json"
            construct_dir(missing_link_redesign_path)
            with open(missing_link_redesign_path, "w", encoding="utf-8") as f:
                json.dump(feedback, f, ensure_ascii=False, indent=2)
            logging.info(f"Saved code missing link redesign to {missing_link_redesign_path}")
            if feedback['class_diagram']==[] and feedback['sequence_diagram']==[]:
                break


        ############## Test Engineer writes tests (to be implemented) #############
        #  Implement TestEngineer and integrate into
        
        final_check_finished_code=developer.memory.get('updated_implementation',[])
        test_engineer= Tester(logging=logging)
        repo_dir= f"{args.output_base_dir}/UC{uc_id}"
        construct_dir(repo_dir)
        test_engineer.construct_src_main_codes(final_check_finished_code,repo_dir)
        # test_engineer.execute_src_main_codes()
        if test_engineer.execute_src_main_codes():
            logging.info(f"Source code in UC{uc_id} compiled failed.")
        else:
            test_cases= test_engineer.act(use_case_json, repo_dir)
            codes_dir= os.path.join(repo_dir, "src", "test", "java")
            # test_engineer.act(use_case_json, codes_dir)
            test_engineer.construct_repo(codes_dir)

            
            for i in range(1,args.check_rounds+1):
                test_engineer.execute_tests(repo_dir)

                feedback_fail_test=test_engineer.run_test_cases()
                if feedback_fail_test == "":
                    break
                codes_after_feedback = developer.update_code_with_tester(feedback_fail_test)
      
                test_engineer.construct_src_main_codes(codes_after_feedback,repo_dir)
                test_engineer.execute_src_main_codes()


        with open(token_path, "r", encoding="utf-8") as f:
            token_dict = json.load(f)
        final_token_path = os.path.join(args.output_token_consumption, f"UC{uc_id}_token_consumption.json")
        construct_dir(final_token_path)
        with open(final_token_path, "w", encoding="utf-8") as f:
            json.dump(token_dict, f, ensure_ascii=False, indent=2)
        logging.info(f"Saved token consumption to {final_token_path}")


        final_time_path = os.path.join(args.output_token_consumption,f"UC{uc_id}_time_consumption.txt")
        construct_dir(final_time_path)
        with open(final_time_path, "w", encoding="utf-8") as f:
            f.write(f"Start Time: {time}\n")
            f.write(f"End Time: {datetime.datetime.now().strftime('%Y-%m-%d_%H-%M-%S')}\n")
main()
    