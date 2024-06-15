# Derivando da imagem oficial do MySQL
#FROM mysql:5.7

# Usar uma imagem base do MariaDB
FROM mariadb:10.6

# Definir variáveis de ambiente para configuração inicial
ENV MYSQL_ROOT_PASSWORD=123
ENV MYSQL_DATABASE=teste
ENV MYSQL_USER=unicap
ENV MYSQL_PASSWORD=123

# Expõe a porta 3306 para permitir comunicação com o servidor MariaDB
EXPOSE 3306

# O comando para iniciar o servidor MariaDB
CMD ["mysqld"]
