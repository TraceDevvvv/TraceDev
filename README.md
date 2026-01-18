# TraceDev: A Traceability-Driven Multi-Agent Framework for
Requirements-to-Code Development

This is the repository of our TraceDev framework.

## Project Structure

- **agent**: The core implementation of TraceDev and the prompts.
- **data**:  The *eTour* and *smos* datasets.
- **evaluate**:  the evaluation scripts for automatic evaluation and statistical analysis.
- **results**:  the execution results of TraceDev and two baseline approaches.

## Research Questions

* **RQ1:** How effective is TraceDev compared with the state-of-the-art multi-agent approaches?
* **RQ2:** How robust is TraceDev when applied with different foundation models?
* **RQ3:** How does each agent contribute to the overall performance of TraceDev?
* **RQ4:** How efficient is TraceDev compared to baseline approaches?

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

