set -e

rm -rf backup
mkdir -p backup
mysqldump -u root -proot portal > backup/emigo.sql
rm -f backup.tgz
tar -czvf backup.tgz backup

