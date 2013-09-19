multitenancyhibernate
=====================

- Project Sample showing a multi tenancy project using Hibernate


1 - Here I use Jboss EAP6 with datasources configurations for each database, if you are using diferent schemas you adopt the same way as a diferent databases approaches. Each Schema has its your owndatasource configuration.
    
    
2 - Configure your project to accept DNS, in this example the project takes the first element of the host as a tenant identifier!

Ex. sample1.host.com.br

sample1 = a datasource name and table name.
