from django.shortcuts import render;
from django.http import HttpResponse;
import json;

json_data = open('./infractions.json')
data1 = json.load(json_data)
data2 = json.dumps(data1)
json_data.close()




def index(request): 
    return HttpResponse("Hello! Please enter a min number of speed infractions in th above URL!")

def requestedInfraction(request, numberInfractions): 
    return HttpResponse([obj for obj in data1 if(obj['infractions_speed'] >= numberInfractions)])
