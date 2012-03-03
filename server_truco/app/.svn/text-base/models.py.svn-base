# -*- coding: utf-8 -*-
from django.db import models

class Supermercado(models.Model):
	nombre = models.CharField(max_length=30, verbose_name=u'Nombre Supermercado')
#	ciudad = models.CharField(max_length=30, verbose_name=u'Ciudad')
#	distintivo = 
#	direccion = 
#	localizacion = models.GeoLocationField..() soñando despierto xD
	def __unicode__(self):
		return unicode(self.nombre)

class TipoProducto(models.Model):
        nombre = models.CharField(max_length=255)
        def __unicode__(self):
                return self.nombre

class Producto(models.Model):
	nombre = models.CharField(max_length = 255, verbose_name = u'Nombre Producto')
	descripcion = models.TextField(verbose_name = u'Descripcion Producto')
	tipo = models.ForeignKey(TipoProducto, verbose_name = u'Tipo de producto')
#	booleano para saber si son marcas blancas propias de cada supermercado o no.
#	image = models.ImageField() -> cliente mejor?
	def __unicode__(self):
		return unicode(self.nombre)

class Mercado(models.Model):
	precio = models.FloatField(verbose_name = u'Precio')
#	cantidad -> no hace falta, es informativo, no nos interesa el stock..
	producto = models.ForeignKey(Producto, verbose_name=u'Producto')
	supermercado = models.ForeignKey(Supermercado, verbose_name=u'Supermercado')
	def __unicode__(self):
		return unicode(self.supermercado) + u' vende ' + unicode(self.producto) + u' por ' + unicode(self.precio) + u' euros'
