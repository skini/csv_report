#!/usr/bin/env python
#Written by Shloka Kini
import sys

p_events_dict = {}

def main(filename):
    #opens file
    temp_file = open(filename, 'r')
    #reads each line from file
    for line in temp_file:
        line = line.strip("\n")
        record = line.split(",")
        #parses each line into its relevant components
        name = record[0]
        time = int(record[1])
        #saves new record or updates old record with counts and average
        if name in p_events_dict:
            new_count = p_events_dict[name][0]+1
            p_events_dict[name] = (new_count, (p_events_dict[name][1] + time)/new_count)
        else:
            p_events_dict[name] = (1, time)
    #prints out results stored in dictionary
    for key,val in p_events_dict.iteritems():
        print "Name: " + key + "/Number of Events Attended: " + str(val[0]) + "/Average time spent: " + str(val[1])
    #

#-------------------------------
if __name__ == "__main__":
    filename = sys.argv[1]
    main(filename)
