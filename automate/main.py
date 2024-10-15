import os
from PyPDF2 import PdfReader, PdfWriter


def extract_title_from_page(page_text):
    lines = page_text.split('\n')
    for i in range(len(lines)):
        if lines[i].strip().startswith(('MR.', 'MS.', 'ENGR.', 'DR.', 'HON.')):
            return f"{lines[i+2].strip()} - {lines[i].strip()} - {lines[i+1].strip()}"
    return None


def save_individual_pdf_pages(input_file_path):
    # Create 'automated_files' folder if it doesn't exist
    output_folder = "automated_files"
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    reader = PdfReader(input_file_path)
    num_pages = len(reader.pages)

    for i in range(0, num_pages, 2):
        page1 = reader.pages[i]
        page1_text = page1.extract_text()
        title_text1 = extract_title_from_page(page1_text)

        if i + 1 < num_pages:
            page2 = reader.pages[i + 1]
            page2_text = page2.extract_text()
            title_text2 = extract_title_from_page(page2_text)
        else:
            page2 = None
            title_text2 = None

        if title_text1:
            output_title = title_text1
        elif title_text2:
            output_title = title_text2
        else:
            output_title = f"Page_{i+1}_to_{i+2}"

        output_title = output_title.replace('\n', ' ').replace('/', '_')
        writer = PdfWriter()
        writer.add_page(page1)
        if page2:
            writer.add_page(page2)

        output_file_path = os.path.join(output_folder, f"{output_title}.pdf")
        with open(output_file_path, 'wb') as output_file:
            writer.write(output_file)

        print(f"Saved: {output_file_path}")


input_pdf = "file.pdf"
save_individual_pdf_pages(input_pdf)
