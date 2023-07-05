import os
from bs4 import BeautifulSoup

# Define o caminho para o arquivo index.html
index_file_path = "./build/reports/jacoco/test/html/index.html"

# Verifica se o arquivo index.html existe
if not os.path.exists(index_file_path):
    print("O arquivo index.html não foi encontrado.")
    exit(1)

# Lê o conteúdo do arquivo index.html
with open(index_file_path, "r", encoding="utf-8") as file:
    content = file.read()

# Analisa o conteúdo HTML com BeautifulSoup
soup = BeautifulSoup(content, "html.parser")

# Encontra o elemento da tabela com a porcentagem de linhas cobertas
line_coverage_element = soup.find("th", string="Line, %")
if line_coverage_element is None:
    print("Elemento 'Line, %' não encontrado no arquivo index.html.")
    exit(1)

# Obtém a porcentagem de cobertura de linhas
line_coverage = line_coverage_element.find_next_sibling("td").find("span", class_="percent").get_text(strip=True)

# Converte a porcentagem em um valor numérico
line_coverage_percentage = float(line_coverage.strip("%"))

# Verifica se a cobertura de linhas é maior ou igual a 90%
if line_coverage_percentage >= 90:
    print(f"A cobertura de linhas é de {line_coverage_percentage}%. Parabéns!")
else:
    print(f"A cobertura de linhas é de {line_coverage_percentage}%. É necessário melhorar a cobertura.")
