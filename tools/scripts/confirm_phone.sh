PHONE=$1
TOKEN=$2

URL=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='server_host_port' limit 1"`;
IAMKEY=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='access_key' limit 1"`;
IAMKEYID=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='access_id' limit 1"`;
REGION=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='app_region' limit 1"`;
SOURCE=`mysql -u root -proot emigo -sN -e "select string_value from config where config_id='source_email' limit 1"`;
export AWS_SECRET_ACCESS_KEY=$IAMKEY
export AWS_ACCESS_KEY_ID=$IAMKEYID

CUR=`date +%s`
MSG=`aws sns publish --region $REGION --message "Welcome to Dikota and thanks for verifying your identity! We'll also use this contact method to keep in touch with you about your account. Please follow this link to confirm your phone number. $URL/#/confirm?phone=$TOKEN" --phone-number $PHONE` || {
  mysql -u root -proot emigo -sN -e "insert into log (level, message, timestamp) values ('error', 'failed to send text to $PHONE', $CUR)"
}

ID=`echo $MSG | jq -r .MessageId`;
echo "$ID"
mysql -u root -proot emigo -sN -e "insert into log (level, message, timestamp) values ('info', 'confirming $PHONE [$ID]', $CUR)"

