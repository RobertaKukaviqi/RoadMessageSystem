# Road Message System

###### Functionalities:
* schedule the messages in the mongodb database and create an endpoint to get the current message 
* endpoint to create a real time message which will stay in display for the specified time 
* afterwards the real time message will be deleted and the scheduled messages will continue displaying 

###### Technologies:
* Spring Boot
* Maven
* MongoDB database
* Hibernate for the persistence layer

###### Steps on realizing this project:
1. create the Message entity which will refer to a collection in the MongoDB database (connected in application.properties)
2. create the repository which will contain the mongodb queries to:
* get the first/current message displayed: db.message.find({}).sort({'dateModified':1});
* findByImportance --> already defined in the MongoRepository
* deleteById(ObjectId id) to remove the document with this objectid based on the scheduled function
3. The scheduled function is implemented in MessageServiceImpl.java which basically retrieves the first message, and deletes it after 10 seocnds. Afterwards it is added again to the collection. This behavior is repeated every 10 seconds, and is achieved through the @Scheduled annotation in Spring Boot.
4. In the controller class (MessageController) we add the 3 required endpoints:
1) Get current message (using DTO to only get the message field)
2) Get all subsequent messages by skipping the first/current one on display
3) endpoint with POST which creates the real-time message. This saves the item in the database, and the scheduled showItem() function will delete this document once it runs out of minutes (added in post body).
