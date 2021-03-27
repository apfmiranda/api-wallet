# api-wallet

##Base de dados PostgreSql com docker

sudo chown -R 1001:1001 /home/alexandrepires/data/postgresql

docker run \
    --name postgresql \
    -v /home/alexandrepires/data/postgresql:/bitnami/postgresql \
    -e POSTGRESQL_USERNAME=postgres \
    -e POSTGRESQL_PASSWORD=password123 \
    -p 5432:5432 \
    bitnami/postgresql:11
