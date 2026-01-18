# base_agent.py
import abc
import json
from typing import Any, Dict, Optional


class BaseAgent(abc.ABC):
    """Generic Base Agent for the Software Engineering Multi-Agent Framework."""

    def __init__(self, role: str, description: str, **kwargs):
        self.role = role
        self.description = description
        self.memory = []

    # ---------- Core Interfaces ----------

    @abc.abstractmethod
    def act(self, **kwargs) -> Any:
        """General action interface for all agents."""
        pass

    # ---------- Utilities ----------

    def run(self, **kwargs) -> Any:
        """Run the agent's main action."""
        pass
