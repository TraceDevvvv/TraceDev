prompt="""
# Role
You are a highly skilled **Test Case Generator Agent**. Your task is to generate a fully functional, compilable, and runnable **single test file** based on the provided source code and a semantic explanation of a test case.

# Input
You will receive the following:

1. **Generated source code** related to a use case.
2. **Test case explanation**, describing the purpose, input, procedure, expected result, and semantic reasoning.
3. **JSON validation output** indicating whether the code matches the test case logic. 

# Task

Using the above input, generate a **single test file** that satisfies the following requirements:

1. **Test File Requirements**

   * Must be fully compilable and runnable in a standard Java environment (or the specified language if provided).
   * Must include all necessary imports, class definition, setup methods, and exactly **one `@Test` method**.
   * Must create realistic test data that matches the description in the test case.
   * Must correctly invoke the methods/functions in the generated source code to test the described functionality.
   * Must assert the expected outcomes (e.g., object creation, exceptions, UI updates, repository updates).
   * Must mock dependencies if necessary (e.g., repositories, dialogs, external services).

2. **Coding Conventions**

   * The test class and method names should be meaningful and derived from the test case description.
   * Include comments linking test steps to the corresponding steps in the test case explanation.
   * Handle exceptions if the test case expects error handling (e.g., `NullPointerException`).

3. **Output Requirements**

   * Output **only the generated test file content**.
   * The test file must contain **exactly one `@Test` method**.
   * Do **not** generate multiple test methods or multiple files.
   * Ensure the file can be compiled and run directly.

# Example Use

**Input:**

* Generated code: 
{code}

* Test case explanation: 
{test_case_explanation}

* JSON match validation:
{match_validation}

**Expected Output:**
A complete, compilable test file (e.g., `DeleteCulturalAssetTest.java`) containing exactly **one `@Test` method**, with necessary setup, invocation, assertions, and  mocking, following the conventions above.

## Output Format (STRICT)
- Output **plain text only**
- Do NOT use JSON, XML, or Markdown
- Do NOT include any explanations outside the code
- Output each Java file using the following exact structure:
- Do NOT add any directory or path prefixes.
- The public class name must exactly match the filename.

=== File: <FileName>.java ===
<complete Java source code>


## Strict Requirements:
* Each file block must start with `=== File:` and end before the next file block
* Do not output anything before the first file block or after the last file block
* generate complete and runnable Java source code files.

"""

path_code = ""
tc_explain_path = ""
our_result_path = ""
import os
import json
final_data = []
for uc_id in range(1,59):
    # prefix=f"SMOS_project{uc_id}_"
    # for dir_name in os.listdir(path_code):
    #     if dir_name.startswith(prefix):
    #         root_dir=os.path.join(path_code, dir_name)
    #         print(f"Processing UC{uc_id} in directory: {root_dir}")
    #         break
    generated_code_dir = os.path.join(path_code,f"UC{uc_id}","src","main","java")
    if not os.path.exists(generated_code_dir):
        print(f"Generated code directory does not exist for UC{uc_id}: {generated_code_dir}")
        continue
    input_dir_TC = os.path.join(tc_explain_path, f"UC{uc_id}_correct.json")
    codes_gen=""
    with open(input_dir_TC, "r", encoding="utf-8") as f:
        tc = f.read()

    for root, _, files in os.walk(generated_code_dir):
        for f in files:
            if f.endswith(".java"):
                path = os.path.join(root, f)
                with open(path, "r") as file:
                    code = file.read()
                    codes_gen += f"\n// File: {f}\n"
                    codes_gen += code + "\n"

    tc_json = json.loads(tc)

    with open(our_result_path, "r", encoding="utf-8") as f:
        responses = [json.loads(line) for line in f]

    for resp in responses:
        uc_idd = resp['uc_id']
        response = resp['response']
        if uc_idd == uc_id:
            if '"match": true' in response:
                match_validation = response
                test_name = resp['test_name']
                for item in tc_json:
                    if item['test_name'] == test_name:
                        tc_explain_text = item
                        break
                final_prompt = prompt.replace("{code}", codes_gen)
                final_prompt = final_prompt.replace("{test_case_explanation}", json.dumps(tc_explain_text, ensure_ascii=False, indent=2))
                final_prompt = final_prompt.replace("{match_validation}", match_validation)
                final_data.append({
                    "uc_id": uc_id,
                    "test_name": test_name,
                    "code": codes_gen,
                    "tc_explain": tc_explain_text,
                    "match_validation": match_validation,
                    "prompt": final_prompt 
                })

with open("", "w", encoding="utf-8") as f:
    for entry in final_data:
        f.write(json.dumps(entry, ensure_ascii=False) + "\n")
    

