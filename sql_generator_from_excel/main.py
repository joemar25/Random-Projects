import os
import pandas as pd

# Load the Excel file
file_path = 'variety.xlsx'

try:
    # Load the Excel sheet into pandas dataframe
    sheet_data = pd.read_excel(file_path, sheet_name='Sheet1')
except FileNotFoundError:
    print(f"Error: The file {file_path} was not found.")
    exit()
except Exception as e:
    print(f"Error reading Excel file: {e}")
    exit()

# Create output directory for SQL files
output_directory = 'sql_inserts_by_region'
if not os.path.exists(output_directory):
    os.makedirs(output_directory)

# Updated mapping of region codes to their actual UUIDs (using "RO" instead of "PO")
region_id_map = {
    "RO1": "256c6ab8-65b9-4574-a19b-bfe8b34cee51",
    "RO2": "fa1cf64e-6170-4ebd-96c9-d31e21975635",
    "RO3": "61f5aa2b-6808-4c7b-8b40-24a2fb800f33",
    "RO4": "436de17a-adad-4ee8-ab2c-e0018492d362",
    "RO5": "45b38c31-58d2-481c-bba7-dd01791464da",
    "RO6": "69baa427-4f56-46ef-a157-a5acf6dee5c2",
    "RO7": "b850899c-8f2a-475c-948e-0d1122a55ed4",
    "RO8": "a3d9e99a-4a90-4504-b9a5-a4b205892d73",
    "RO9": "fd8e77d8-4615-413d-818b-df9fe0276b62",
    "RO10": "0cf345a4-68fc-408f-94b8-e71050abe52c",
    "RO11": "795454c2-26fd-4825-8feb-3f7c8441401a",
    "RO12": "5ba97c6b-60eb-4699-9a16-729bde4211c2"
}

# Debugging: Print the columns to check their names
print("Columns found in the Excel file:", sheet_data.columns)

# Function to generate SQL inserts for all regions, grouped by RICE and CORN


def generate_sql_inserts(sheet_data):
    # Iterate over the columns two by two (RICE, CORN)
    for col in range(0, sheet_data.shape[1], 2):
        region = sheet_data.columns[col]

        # Handle cases where column name is "Unnamed" and match the correct region name
        if "Unnamed" in region:
            continue

        # Skip regions not mapped
        if region not in region_id_map:
            print(f"Skipping region {region} (not in the region_id_map).")
            continue

        region_uuid = region_id_map[region]

        # RICE column, keep the text exactly as is, skip header row
        rice_col = sheet_data[region].dropna()[1:]  # Skip header row
        corn_col = sheet_data[sheet_data.columns[col + 1]
                              ].dropna()[1:]  # Skip header row for CORN

        # Debugging: Check if there are seeds in the RICE and CORN columns
        print(f"Generating SQL for region: {region} (UUID: {region_uuid})")
        print(f"  RICE seeds count: {len(rice_col)}")
        print(f"  CORN seeds count: {len(corn_col)}")

        # SQL file path for the current region (filename will be region_seeds_inserts.sql)
        sql_file_path = os.path.join(
            output_directory, f'{region}_seeds_inserts.sql')

        # Open the SQL file for writing
        with open(sql_file_path, 'w') as f:
            f.write(f"-- SQL for {region}\n")

            # Write SQL for Rice seeds (lowercase "rice")
            f.write(f"-- rice seeds\n")
            for seed in rice_col:
                f.write(f"INSERT INTO seeds (seed, seed_type, region_id) VALUES ('{
                        seed}', 'rice', '{region_uuid}');\n")

            # Write SQL for Corn seeds (lowercase "corn")
            f.write(f"-- corn seeds\n")
            for seed in corn_col:
                f.write(f"INSERT INTO seeds (seed, seed_type, region_id) VALUES ('{
                        seed}', 'corn', '{region_uuid}');\n")

        print(f"SQL insert statements have been generated and saved to {
              sql_file_path}")


# Generate SQL inserts for all regions
generate_sql_inserts(sheet_data)

print("SQL insert statements for all regions have been generated.")
