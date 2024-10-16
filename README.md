# Friendfin Backend
FriendFin é o backend para uma aplicação dedicada ao monitoramento e denúncia de pesca ilegal de tubarões. Este projeto utiliza Docker Compose para facilitar o desenvolvimento e a execução, permitindo que o servidor seja iniciado em contêineres Docker de forma eficiente.
Requisitos
Antes de começar, você precisará ter instalado em sua máquina:

Docker
Docker Compose

# Executando o Projeto
Construir a Imagem Docker: No terminal, navegue até o diretório do projeto e execute:

docker-compose build

Iniciar os Contêineres: Após a construção da imagem, você pode iniciar os contêineres usando:

docker-compose up

Acessar a Aplicação: A aplicação estará disponível em http://localhost:8080.

Parar os Contêineres: Para parar os contêineres em execução, pressione Ctrl + C ou execute:

docker-compose down