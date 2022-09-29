                                   dynamic-scheduler project documentation

Project for executing daily queries to the database through a scheduler (cron).



To start a project, open PostgreSQL and gradually follow these queries:
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

create database scheduler_db;

create table product(
    id bigint primary key,
    name varchar,
    model varchar,
    price double precision
);

create sequence product_id_seq;

alter sequence product_id_seq owner to postgres;

alter sequence product_id_seq owned by product.id;



List of endpoints, examples of requests to them and examples of answers
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Description:
API to check if the service is working
Endpoint:
GET http://localhost:8081/schedule/sayHello
Request:
Body does not exist
Response:
{
    "responseBody": "Hello",
    "statusCode": 200,
    "errorMessage": null,
    "dateTime": "2022-09-29T13:57:53.7402175"
}


Description:
API for creating a new job in scheduler
Endpoint:
POST http://localhost:8081/schedule/createJob
Request:
{
    "name": "remove first product",
    "cronExpression": "0/15 * * * * *",
    "queryToDB": "delete from product where id = (select min(id) from product);"
}
Response:
{
    "responseBody": {
        "id": "75e6ec8d-1c64-428f-90df-320e9eabd581",
        "name": "remove first product",
        "queryToDB": "delete from product where id = (select min(id) from product);",
        "cronExpression": "0/15 * * * * *",
        "enabled": true
    },
    "statusCode": 200,
    "errorMessage": null,
    "dateTime": "2022-09-29T14:32:15.0965585"
}


Description:
API to get all tasks from the scheduler (running and disabled)
Endpoint:
GET http://localhost:8081/schedule/getJobs
Request:
Body does not exist
Response:
{
    "responseBody": [
        {
            "id": "b483c756-aacb-415a-8d69-472c7879167d",
            "name": "remove first product",
            "queryToDB": "delete from product where id = (select min(id) from product);",
            "cronExpression": "0/15 * * * * *",
            "enabled": true
        },
        {
            "id": "e1edfb10-57e5-4c32-aaef-310670eb0545",
            "name": "most expensive 3 products",
            "queryToDB": "select * from product order by price desc limit 3;",
            "cronExpression": "0/20 * * * * *",
            "enabled": true
        }
    ],
    "statusCode": 200,
    "errorMessage": null,
    "dateTime": "2022-09-29T14:33:54.7530919"
}


Description:
API to remove a task from the scheduler by task ID
Endpoint:
DELETE http://localhost:8081/schedule/removeJob/{JobID}
Request:
Body does not exist
Response:
{
    "responseBody": "b483c756-aacb-415a-8d69-472c7879167d job removed!",
    "statusCode": 200,
    "errorMessage": null,
    "dateTime": "2022-09-29T12:07:28.3795236"
}


Description:
API to pause a task from the scheduler by task ID
Endpoint:
GET http://localhost:8081/schedule/pauseJob/{JobID}
Request:
Body does not exist
Response:
{
    "responseBody": "b483c756-aacb-415a-8d69-472c7879167d job paused!",
    "statusCode": 200,
    "errorMessage": null,
    "dateTime": "2022-09-29T12:09:30.9376233"
}


Description:
API to restart a task from the scheduler by task ID
Endpoint:
GET http://localhost:8081/schedule/restartJob/{JobID}
Request:
Body does not exist
Response:
{
    "responseBody": "b483c756-aacb-415a-8d69-472c7879167d job restarted!",
    "statusCode": 200,
    "errorMessage": null,
    "dateTime": "2022-09-29T12:11:03.4920316"
}



'/schedule/createJob' endpoint requests examples
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

---create new product every 10 seconds request---
{
    "name": "create new product",
    "cronExpression": "0/10 * * * * *",
    "queryToDB": "insert into product (id, name, model, price) values ((nextval('product_id_seq')), 'Nokia', (concat('M', (select last_value from product_id_seq))), (trunc((random() * 1000)::numeric, 2)));"
}


---delete first product every 15 seconds request---
{
    "name": "remove first product",
    "cronExpression": "0/15 * * * * *",
    "queryToDB": "delete from product where id = (select min(id) from product);"
}


---get most expensive 3 products every 20 seconds request---
{
    "name": "most expensive 3 products",
    "cronExpression": "0/20 * * * * *",
    "queryToDB": "select * from product order by price desc limit 3;"
}



Token for http requests:
Basic dXNlcjpwYXNzd29yZDE=