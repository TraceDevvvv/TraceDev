
chatdev_path = ""
path_target = chatdev_path
TC_EXPLAIN_DIR = "data/eTour/test_case_explain"
prompt="""
You are a **software testing validation assistant (LLM-as-a-Judge)**.

## 【Task】

You will be given:

1. A **test case described in natural language** (groundtruth format), which may include fields such as:

   * `input_data`
   * `procedure`
   * `expected_result`

2. A **generated source code file** (gen-code-file).

Your task is to **judge whether the generated source code semantically implements the core algorithm and logic implied by the test case**.

---

## 【Important Clarifications】

* The fields **`input_data`**, **`procedure`**, and **`expected_result`** in the test case are **illustrative examples**, not strict implementation requirements.
* The code **does NOT need to exactly reproduce**:

  * the same input values,
  * the same execution steps,
  * or the same output format.
* These fields exist **only to clarify the intended behavior and semantics** of the test case.

👉 **Your judgment must focus on whether the *core logical intent* of the test case is implemented**, not on literal matching.

---

## 【Evaluation Rules】

* Focus on **functional semantics and algorithmic behavior**, NOT on:

  * function names,
  * variable names,
  * class names,
  * code structure,
  * or programming language constructs.

* If the test case describes a behavior (e.g., *“deleting an existing entity should remove it and return success”*), then:

  * Any code that correctly implements that behavior **counts as a match**,
  * Even if implemented with different naming, structure, or control flow.

* Only return **match = false** if:

  * The **core logic or behavior implied by the test case is missing**, or
  * The implemented logic **contradicts the intended semantics**.

---

## 【Decision Criteria】

* ✅ **Return `match: true`**
  If the generated code **semantically fulfills the intent and behavior** described by the test case.

* ❌ **Return `match: false`**
  If the generated code **fails to implement, or incorrectly implements, the core logic** described by the test case.

---
## 【Input】
Test case description (groundtruth): 
{tc_explain_text} 

Generated code file: {code_text}


## 【Output Format】
Return **only one JSON object**, and **nothing else**:

```json
{"match": true, "reason": "<explanation of how the code's logic semantically matches the test case intent>"}
```
or
```json
{"match": false, "reason": "<explanation of why the core semantic logic is missing or incorrect>"}
```

🚫 Do NOT include explanations outside the JSON object.
"""

import os
import json
import glob
def get_json_files(directory: str):
    json_files = [f for f in os.listdir(directory) if f.endswith(".json")]
    return json_files
prompt_list = []
for uc_id in range(52,59):
    if uc_id == 37:
        continue
    # prefix = f"UC_project{uc_id}_"
    # matched_dirs = glob.glob(f"{path_target}/{prefix}*")
    # if not matched_dirs:
    #     print(f"[INFO] No generated code directory found for UC{uc_id} with prefix {prefix}, skipping.")
    #     continue
    # print(f"[INFO] Processing UC{uc_id} in directory {matched_dirs[0]}")
    # generated_code_dir = matched_dirs[0]
    generated_code_dir = os.path.join(path_target, f"UC{uc_id}","src","main","java")
    # generated_code_dir = os.path.join(path_target, f"UC{uc_id}")

    codes_gen=""

    for root, _, files in os.walk(generated_code_dir):
        for f in files:
            if f.endswith(".java"):
                path = os.path.join(root, f)
                with open(path, "r") as file:
                    code = file.read()
                    codes_gen += f"\n// File: {f}\n"
                    codes_gen += code + "\n"
    input_dir_TC = os.path.join(TC_EXPLAIN_DIR, f"UC{uc_id}")
    json_files= get_json_files(input_dir_TC)
    for json_file in json_files:
        tc_path =  os.path.join(input_dir_TC, json_file)
        with open(tc_path, "r", encoding="utf-8") as f:
            tc = f.read()
        tc_json = json.loads(tc)
        for item in tc_json:
            test_name = item.get("test_name")
            prompt1 = prompt.replace("{tc_explain_text}", json.dumps(item, ensure_ascii=False, indent=2))
            prompt1 = prompt1.replace("{code_text}", codes_gen)
            prompt_list.append({
                "uc_id": uc_id,
                "test_name": test_name,
                "prompt": prompt1
            })
write_path = ""
if not os.path.exists(os.path.dirname(write_path)):
    os.makedirs(os.path.dirname(write_path))
with open(write_path, "w", encoding="utf-8") as f:
    json.dump(prompt_list, f, ensure_ascii=False, indent=4)
    