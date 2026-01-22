# TraceDev: A Traceability-Driven Multi-Agent Framework for Requirements-to-Code Development

This is the repository of our TraceDev framework. TraceDev is a traceability-driven multi-agent framework for automated software development grounded in industrial use cases. TraceDev comprises five role-specific agents: Requirement Refiner, Designer, Developer, Tester, and Validator.
At the core of TraceDev is the Validator Agent, which constructs and maintains a heterogeneous traceability graph linking requirements, design models, and code artifacts. This traceability mechanism enables fine-grained verification throughout the development process and helps ensure requirement completeness in the generated code.
We evaluate TraceDev on two widely used datasets, covering a total of 125 use cases, and compare it against two representative multi-agent frameworks, MetaGPT and ChatDev.

## Project Structure

- **agent**: The core implementation of TraceDev and the prompts.
- **data**:  The *eTour* and *smos* datasets.
- **evaluate**:  The evaluation scripts for automatic evaluation and statistical analysis.
- **results**:  The execution results of TraceDev and two baseline approaches.


## Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/TraceDevvvv/TraceDev
cd TraceDev
```

### 2.LLM Configuration

You can configure ` agent/config/config.ymll` :

```yml
agent_api_key : "YOUR_API_KEY"
agent_api_url : "https://api.openai.com/v1"  # or forward url / other llm url
agent_model_name : "deepseek-v3.2"  
```

### 3. Run

```bash
python ./agent/main.py \
  --model deepseek-v3.2 \
  --method TraceDev \
  --dataset eTour \
  --uc-case-dir ./data/eTour/req \
```

### 4. View Results

Results are saved in the `./results` directory:

