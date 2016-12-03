#!/usr/local/bin/python3
import json

def convert(schedules):
    for ss in schedules:
        earliest_time = ss["earliest_time"]
        eartime = []
        schedule = {}
        for key in earliest_time:
            schedule["time"] = earliest_time[key]
            schedule["station"] = key
            eartime.append(schedule)
        ss["earliest_time"] = eartime
        latest_time = ss["latest_time"]
        eartime = []
        schedule = {}
        for key in latest_time:
            schedule["time"] = latest_time[key]
            schedule["station"] = key
            eartime.append(schedule)
        ss["latest_time"] = eartime

def process(data):
    contents = data["answer"][0]["content"]
    for c in contents:
        lines = c["lines"]
        for l in lines:
            station_schedules = l["station_schedule"]
            convert(station_schedules)
            line_schedules = l["line_schedule"]
            convert(line_schedules)
    fout = open("output.json", 'wb')
    fout.write(json.dumps(data, ensure_ascii = False).encode('utf-8'))
    fout.close()

with open("test.json", "rb") as f:
    try:
        data = f.read()
        jdata = json.loads(data.decode("UTF-8"))
        process(jdata)
    except IOError as ioerr:
        print("File error: " + str(ioerr))
