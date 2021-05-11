# Courses Platform

This is a Rest API built with Spring Framework that emulates a Courses platform. 

# Endpoints: 

### Categories Controller:

GET /api/v1/categories listAction

POST /api/v1/categories createAction

GET /api/v1/categories/{categoryId}     getCategoryAction

PUT /api/v1/categories/{categoryId}     updateCategoryAction

DELETE/api/v1/categories/{categoryId}   deleteAction

POST /api/v1/categories/savebatch       saveAllCategoriesAction



### Course Controller:

GET /api/v1/categories/{categoryId}/courses getAllCoursesByCategoryIdAction

POST /api/v1/categories/{categoryId}/courses createCourseAction

PUT /api/v1/categories/{categoryId}/courses/{courseId} updateCourseAction

GET /api/v1/courses/{courseId} getCourseAction

DELETE /api/v1/courses/{courseId} deleteAction


### Lesson Controller

GET /api/v1/courses/{courseId}/lessons getAllAction

POST /api/v1/courses/{courseId}/lessons createAction

PUT /api/v1/courses/{courseId}/lessons/{lessonId} updateAction

POST /api/v1/courses/{courseId}/lessons/savebatch saveAllAction

GET /api/v1/lessons/{lessonId} getOneAction

DELETE /api/v1/lessons/{lessonId} deleteAction
