import xml.etree.ElementTree as ET

def check_coverage():
    tree = ET.parse('./build/reports/jacoco/test/jacocoTestReport.xml')
    root = tree.getroot()

    total_lines = 0
    covered_lines = 0

    for package in root.findall('package'):
        for clazz in package.findall('class'):
            for sourcefile in clazz.findall('sourcefile'):
                for line in sourcefile.findall('line'):
                    total_lines += 1
                    if line.attrib['ci'] != '0':
                        covered_lines += 1

    coverage_percentage = (covered_lines / total_lines) * 100

    if coverage_percentage >= 90:
        print('Coverage meets the minimum requirement.')
    else:
        print('Coverage does not meet the minimum requirement.')

check_coverage()
