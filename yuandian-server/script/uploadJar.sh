#!bin/bash
cd ../
project_name='yuandian-netty/target/yuandian-netty-1.0-SNAPSHOT.jar'
echo 'begin upload file'
scp ${project_name}  yuandian:/root/
echo 'upload file done'
ssh yuandian "sh restart_yuandian_server.sh"
echo 'server restart successful'
