#! /bin/bash
procs=$(ps -ef | grep sso-config | wc -l); 
until [ $procs -lt "2" ]; do
       ipAddress=$(ifconfig utun0 | grep inet | awk '{print $2}');
       curl -v  http://192.168.200.174:8181/checkIn?name=$(whoami)\&ip=$ipAddress
       procs=$(ps -ef | grep sso-config | wc -l); 
       sleep 300
done
echo WAM Not running;