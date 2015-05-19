Heroku-Spring Data JPA-JerseyRest
=======================

Product Catalog Service using JPARepository + JerseyRest + ready for Heroku

More info on each project:

* #### Spring Data JPA: 
<a href="http://blog.springsource.org/2011/02/10/getting-started-with-spring-data-jpa/">SpringSource</a>

* #### Jersey JAX-RS
Library for Restful Web Services
<a href="http://jersey.java.net/">Jersey</a>

This project is able to use Vaadin as Front End. To enable it, uncomment its servlet in web.xml
* #### Vaadin
The best Java user interface for web applications
<a href="http://www.vaadin.com">Vaadin</a>



How-to:
-------------

### Create an Heroku account

You can register on Heroku for free, just follow [this link](www.heroku.com) and follow Heroku instructions

### Download Heroku Toolbelt

Download and install Heroku Toolbelt (it is in the Heroku website)

### Clone this repository

git-clone this repository to a target [folder] on your system

### Create a new Heroku server

Now we are going to create a new server for the app, go into [folder] and type:

	heroku create

this command should return something like:

	Creating safe-escarpment-6048... done, stack is cedar
	http://safe-escarpment-6048.herokuapp.com/ | git@heroku.com:safe-escarpment-6048.git
	Git remote heroku added

which means heroku created a server with name safe-escarpment-6048, which is a really confusing name, so we change it by using:

	heroku apps:rename [type_a_new_name]

this command should return something like this:

	Renaming safe-escarpment-6048 to drmillan-javaserver... done
	http://[new_name].herokuapp.com/ | git@heroku.com:[new_name].git

### Create a new PostgreSQL database

Lets add a new database to our heroku server, type in your command line:

	heroku addons:create heroku-postgresql

you should get this result:

	Adding heroku-postgresql on [new_name]… done, v3 (free)
	Attached as HEROKU_POSTGRESQL_ORANGE_URL
	Database has been created and is available
	 ! This database is empty. If upgrading, you can transfer
	 ! data from another database with pgbackups:restore.
	Use heroku addons:docs heroku-postgresql to view documentation.

Ok, at this moment you have a brand new PostgreSQL database on heroku, (a free one, you are limited to 10000 rows).

The project will use the heroku provided DATABASE_URL environment variable to expose the database.
To know its value, run this command:

	heroku config | grep DATABASE_URL
	
In order to test locally, the jetty server should be started with a modified version of the DATABASE_URL
environment variable which heroku uses

	DATABASE_URL=${DATABASE_URL}?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory

### Deploy server

You can push the project into heroku by using 

	git push heroku master


### Test Services

And now you can load the website:

	http://[new_name].herokuapp.com

To test locally, replace [new_name] with localhost:<port> depending on which port was
jetty started.

## That's All

And that's all, now you can play with JPA database connections and JAX-RS rest services

## Test the real thing

You can test this project for real In the following link:

[http://mscproductcatalog.herokuapp.com](http://mscproductcatalog.herokuapp.com)

Thanks all!
Lingow.
Ivan.
Mike
