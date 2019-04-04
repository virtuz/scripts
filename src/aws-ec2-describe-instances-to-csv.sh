#!/bin/bash

# Maintainer: virtuz.blr@gmail.com 
# Description: Extract list of AWS EC2 to CSV
# Reference: https://docs.aws.amazon.com/cli/latest/reference/ec2/describe-instances.html

FILENAME=aws-ec2-describe-instances-$(date +%s%N).csv
FIELDS='InstanceId'
FIELDS=${FIELDS}',Placement.AvailabilityZone'
FIELDS=${FIELDS}',InstanceType'
FIELDS=${FIELDS}',ImageId'
FIELDS=${FIELDS}',EbsOptimized'
FIELDS=${FIELDS}',EnaSupport'
FIELDS=${FIELDS}',State.Name'
FIELDS=${FIELDS}',LaunchTime'
FIELDS=${FIELDS}',[Tags[?Key==`Name`].Value] [0][0]'
FIELDS=${FIELDS}',IamInstanceProfile.Arn'
FIELDS=${FIELDS}',VpcId'
FIELDS=${FIELDS}',SubnetId'
FIELDS=${FIELDS}',Hypervisor'
FIELDS=${FIELDS}',VirtualizationType'
FIELDS=${FIELDS}',[BlockDeviceMappings[?DeviceName==`/dev/sda1`].Ebs.VolumeId] [0][0]'

# Report header
echo $FIELDS > $FILENAME

# Report body
aws ec2 describe-instances --query "Reservations[].Instances[][].[${FIELDS}]" --output text | tr "\\t" "," >> $FILENAME

# Output
echo "Report has been saved into ${FILENAME}"
