# Courses Platform

This is a Rest API built with Spring Framework that emulates a Courses platform. 

# Endpoints: 

### Categories Controller:

GET /api/v1/categories listAction

POST /api/v1/categories createAction

GET /api/v1/categories/{categoryId}     

PUT /api/v1/categories/{categoryId}     

DELETE/api/v1/categories/{categoryId}   

POST /api/v1/categories/savebatch       



### Course Controller:

GET /api/v1/categories/{categoryId}/courses 

POST /api/v1/categories/{categoryId}/courses 

PUT /api/v1/categories/{categoryId}/courses/{courseId} 

GET /api/v1/courses/{courseId} 

DELETE /api/v1/courses/{courseId} 


### Lesson Controller

GET /api/v1/courses/{courseId}/lessons 

POST /api/v1/courses/{courseId}/lessons 

PUT /api/v1/courses/{courseId}/lessons/{lessonId} 

POST /api/v1/courses/{courseId}/lessons/savebatch 

GET /api/v1/lessons/{lessonId} 

DELETE /api/v1/lessons/{lessonId} 
