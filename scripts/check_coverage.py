import xml.etree.ElementTree as ET

def check_coverage():
    tree = ET.parse('./build/reports/jacoco/test/jacocoTestReport.xml')
    root = tree.getroot()

    total_lines = 0
    covered_lines = 0

    for package in root.findall('.//package'):
        for sourcefile in package.findall('.//sourcefile'):
            total_lines += int(sourcefile.attrib['lines'])
            covered_lines += int(sourcefile.attrib['covered'])

    coverage_percentage = (covered_lines / total_lines) * 100

    if coverage_percentage >= 90:
        print('Coverage meets the minimum requirement.')
    else:
        print('Coverage does not meet the minimum requirement.')

check_coverage()
