import streamlit as st
import requests
import pandas as pd

API_URL = "http://localhost:8080/search"

st.set_page_config(page_title="Service ID Code Scanner", layout="wide")

st.title("🔍 Service ID Code Scanner")

repo = st.text_input("GitHub Repository URL")
service_id = st.text_input("Service ID")

if st.button("Search Code"):

    with st.spinner("Scanning repository..."):

        response = requests.post(
            API_URL,
            params={
                "repoUrl": repo,
                "serviceId": service_id
            }
        )

        if response.status_code == 200:

            data = response.json()

            if len(data) == 0:
                st.warning("No matches found")

            else:
                df = pd.DataFrame(data)

                st.success(f"{len(df)} matches found")
                st.dataframe(df)

                for row in data:
                    st.code(
                        f"{row['fileName']} (Line {row['lineNumber']})\n{row['snippet']}"
                    )

        else:
            st.error("Backend error")
