import os
from bs4 import BeautifulSoup

# Caminho do relatório de cobertura
report_path = "./build/reports/jacoco/test/html/index.html"

# Verifica se o arquivo de relatório existe
if not os.path.isfile(report_path):
    print("O arquivo de relatório não foi encontrado.")
    exit(1)

# Abre o arquivo de relatório
with open(report_path, "r") as f:
    content = f.read()

# Analisa o conteúdo HTML do relatório
soup = BeautifulSoup(content, "html.parser")

# Extrai a porcentagem de linhas cobertas
line_coverage_element = soup.find("th", text="Line, %")
line_coverage = line_coverage_element.find_next_sibling("td").text.strip()

# Converte a porcentagem em um número decimal
line_coverage = float(line_coverage.rstrip("%")) / 100

# Verifica se a cobertura de linha é maior ou igual a 90%
if line_coverage >= 0.9:
    print("A cobertura de linha é satisfatória.")
else:
    print("A cobertura de linha é insuficiente.")
