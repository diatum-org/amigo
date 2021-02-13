EMAIL=$1
TOKEN=$2

URL=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='server_host_port' limit 1"`;
IAMKEY=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='access_key' limit 1"`;
IAMKEYID=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='access_id' limit 1"`;
REGION=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='app_region' limit 1"`;
SOURCE=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='source_email' limit 1"`;
export AWS_SECRET_ACCESS_KEY=$IAMKEY
export AWS_ACCESS_KEY_ID=$IAMKEYID

HTML="<div style=\"display: block; margin: auto; max-width: 315px; width: fit-content; padding: 16px; font-family: 'Roboto', sans-serif;\"><img style=\"display: block; margin: auto; width: 100%;\" src=\"$URL/assets/logo.png\"></img><div style=\"padding-top: 16px; color: #51515E; text-align: center;\"><a href=\"$URL/#/reset/$TOKEN\" target=\"_blank\">Please follow this link to reset your Dikota password.</a></div>";

CUR=`date +%s`
MSG=`aws ses send-email --region $REGION  --from $SOURCE --destination "{ \"ToAddresses\": [ \"$EMAIL\" ] }" --subject 'Dikota Account Password Reset'  --html "$HTML"` || {
  mysql -u root -proot emigo -sN -e "insert into log (level, message, timestamp) values ('error', 'failed to send email to $EMAIL', $CUR)"
}

ID=`echo $MSG | jq -r .MessageId`;
mysql -u root -proot emigo -sN -e "insert into log (level, message, timestamp) values ('info', 'resetting $EMAIL [$ID]', $CUR)"

