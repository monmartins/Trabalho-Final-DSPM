#!/usr/bin/python
# coding=utf-8
from flask import Flask, request, send_file, send_from_directory, make_response, jsonify
from flask_restful import Resource, Api
from pymongo import MongoClient
from flask_restful import reqparse
from bson.json_util import dumps
import hashlib
from math import radians, cos, sin, asin, sqrt


from flask import redirect, url_for
from werkzeug.utils import secure_filename
import os
from werkzeug import security

app = Flask(__name__, static_folder='static')

connection = MongoClient('localhost', 27017)
db = connection['app']

collectionT = db['transferencias']
collectionR = db['receitas']
collectionG = db['gastos']

# Por no local de images/ o path do folder de armazenagem das imagens
app.config['UPLOAD_FOLDER'] = '/home/r/'
app.config['ALLOWED_EXTENSIONS'] = set(['png', 'jpg', 'jpeg', 'gif'])


parser = reqparse.RequestParser()

api = Api(app)
todos = {}


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1] in app.config['ALLOWED_EXTENSIONS']

'''
class TodoSimple(Resource):
    def get(self, todo_id):
         fulldata = dict()
         data = collection.find({todo_id})
         for x in data:
            fulldata.append(x)
         print(fulldata)
         return jsonify(fulldata)

    def put(self, todo_id):
        todos[todo_id] = request.form['data']
        a = collection.insert(todos)
        return collection.find_one(a) == todos

api.add_resource(TodoSimple, '/<string:todo_id>')
'''

def haversine(lon1, lat1, lon2, lat2):
	lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, lon2, lat2])
	# haversine formula
	dlon = lon2 - lon1
	dlat = lat2 - lat1
	a = sin(dlat/2.0)**2.0 + cos(lat1) * cos(lat2) * sin(dlon/2.0)**2.0
	c = 2.0 * asin(sqrt(a))
	km = 6367.0 * c
	return km	 
class Query(Resource):
    @staticmethod
    def get():
        # """
        #  Default to 200 OK
        #  Coletando campo auth do metodo request to python
        #  get('http://localhost:5000/user',auth=('Victor','eita')).json()
        # """
        auth = request.authorization

        print(auth)
        # formato do auth {'username': nome_do_usuario , 'password':'senha_do_usuario'}
        name = auth['username']
        password = auth['password']
        password = hashlib.sha1(password).hexdigest()

        # print(name)
        # print(password)

        data = collectionT.find({'Codigo Funcao': name, 'password': password})
        if 2 > data.count() > 0:
            return "Autenticacao valida"
        else:
            return "Falha na auntenticacao"

    @staticmethod
    def post():
        """
         Utilizando parser para coletar os dados passados na request
         put('http://localhost:5000/user', data={'name': 'Victor','pass':'eita'}).json()
         files = {’file’: open(’report.xls’, ’rb’)}
         requests.post('http://localhost:5000/user',data={'name': 'Victor','pass':'eita'},files=files)
        """
        parser.add_argument('codigoFuncao')


        query = parser.parse_args()        
        # Carregando dados e criptografando a senha com hash sha1
        # name = user['username']
        # password = user['password']
        # password = hashlib.sha1(password).hexdigest()


        #files = {'file': open('/home/r/workspace/s.png', 'r')}
        #requests.post('http://localhost:8000/user',data={'username': 'Victor','password':'eita'},files=files)

        print dumps(collectionR.find({'Mes':292974}))
        t = int(query['codigoFuncao'])
        print t
        print "oi"
        return 1


    @staticmethod
    def put():
		"""
		Utilizando parser para coletar os dados passados na request
		put('http://localhost:5000/user', data={'name': 'Victor','pass':'eita'}).json()
		"""
		parser.add_argument('name')
		parser.add_argument('pass')
		user = parser.parse_args()

		# Carregando dados e criptografando a senha com hash sha1
		name = user['name']
		password = user['pass']
		password = hashlib.sha1(password).hexdigest()

		# print(name)
		# print(password)
		data = collectionU.find({'name': name})
		print(data.count())
		if 2 > data.count() > 0:
			collectionU.update_one({'name': name, 'password': password})
			return "Usuario Recadastrado"


class Feed(Resource):  
	@staticmethod
	def get():
		"""
		Default to 200 OK
		Coletando campo auth do metodo request to python
		get('http://localhost:5000/user',auth=('Victor','eita')).json()
		"""
		auth = request.authorization

		# print(auth)
		# formato do auth {'username': nome_do_usuario , 'password':'senha_do_usuario'}
		name = auth['username']
		password = auth['password']
		password = hashlib.sha1(password).hexdigest()

		# print(name)
		# print(password)

		data = collectionR.find({'name': name, 'password': password})
		if 2 > data.count() > 0:
			return 'Autenticacao valida'
		else:
			return "Falha na auntenticacao"

	@staticmethod
	def post():
		parser.add_argument('lat')
		parser.add_argument('_long')
		geo = parser.parse_args()
		parser.add_argument('name')
		parser.add_argument('address')
		parser.add_argument('geoposition')
		parser.add_argument('url_image')
		user = parser.parse_args()

		print float(geo['lat'])
		print float(geo['_long'])

		# Carregando dados e criptografando a senha com hash sha1
		name = user['name']
		# address = user['address']
		# geoposition = user['geoposition']
		# lat = geoposition['lat']
		# longi = geoposition['longi']
		# url_image = user['url_image']
		# data = collectionR.find({'name': name, 'address': address, 'geoposition': geoposition})


		#distance = haversine(geo['longi'],geo['lat'],)
		data = collectionR.find()
		retrieve_data = []
		for dump in data:
			restaurant = []
			restaurant.append((dump['name']))
			restaurant.append((dump['address']))
			distance = haversine(float(geo['_long']),float(geo['lat']),float(dump['geoposition']['longi']),float(dump['geoposition']['lat'])) * 0.90
			print distance
			restaurant.append(distance)
			restaurant.append((dump['url_image']))
			retrieve_data.append(restaurant)
		print retrieve_data
		return retrieve_data
			#print retrieve_data
			# if name == "all":
			# 	return data
			# # print(data.count())
			# if data.count() > 0:
			#     collectionR.insert_one(
			#         {'name': name, 'address': address, 'geoposition': geoposition, 'url_image': url_image})
			#     return "Restaurante cadastrado"
	@staticmethod
	def put():
		parser.add_argument('name')
		parser.add_argument('address')
		parser.add_argument('geoposition')
		user = parser.parse_args()

		# Carregando dados e criptografando a senha com hash sha1
		name = user['name']
		address = user['address']
		geoposition = user['geoposition']

		data = collectionR.find({'name': name, 'address': address, 'geoposition': geoposition})
		print(data.count())
		if 2 > data.count() > 0:
			collectionR.update_one({'name': name, 'address': address, 'geoposition': geoposition})
			return "Restaurante Recadastrado"




api.add_resource(Query, '/query')
api.add_resource(Feed, '/feed')

if __name__ == '__main__':
    # app.run(debug=True)
    app.run(host='0.0.0.0', port=8000, debug=True)
