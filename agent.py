from __future__ import annotations
 
from tools.ssl_tools import check_endpoints
from gemini_client import gemini
from models import ServiceContext, SSLEndpoint, SSLCheckResult
 
class SSLAgent:
    """
    Agent role: Observe SSL verification outcomes and interpret them.
    Uses:
    - Tool: check_endpoints() to collect real signals
    - LLM: Gemini to reason over results and output next actions
    """
 
    def run(self, service: ServiceContext, endpoints: list[SSLEndpoint]) -> tuple[list[SSLCheckResult], str]:
        results = check_endpoints(endpoints)
 
        prompt = f"""
Service ID: {service.service_id or "<not-set>"}
 
Given these SSL results, explain:
1) what failed
2) why it likely failed
3) next actions
 
Results JSON:
{[r.model_dump() for r in results]}
"""
        interpretation = gemini.generate_text(prompt).strip()
        return results, interpretation