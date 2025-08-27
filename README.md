# AZ Journey Prod App-Extractor tool
A tool that extracts a Journey application data from DSS Prod and generates sql queries (Insert queries and Select queries) for each table.

**Input:** 14-digit application number or 9-digit policy number

**Output:** creates 2 sql files - one for insert queries and for select queries

To initialize the output directory for generated queries, 
1. Open the application.properties
2. Change the value for **project.local.root_dir=** 
