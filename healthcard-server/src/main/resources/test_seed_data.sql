
//Employee inserts
insert into employee(id,email,fl_id,name,status,role) values(1,"ravi.rampalli@fissionlabs.com",'1782',"Ravi Rampalli","active",'DM');
insert into employee(id,email,fl_id,name,status,role) values(2,"pavan.arra@fissionlabs.com",'1404',"Pavan Arra","active",'DM');

//Clients//
insert into project_healthcard.client(id,client_name,customer_value,status) values(1,'Naghi','10','active');
insert into project_healthcard.client(id,client_name,customer_value,status) values(2,'Krysp','10','active');


//component//
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(1,'Schedule','active','1','Overall, what is the risk level for the project?<p> (1 - major/significant risks; 5 - minor/no risks)');
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(2,'Resourcing','active','1','How well did the project adhere to the Schedule for the last month?<p> (1 - all deliverables missed by a big margin; 5 - All deliverables were completed on or before time)');
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(3,'Utilization/Budget','active','1','How well is the project resourced? <p>(1 - Missing key/many resources/people; 5 - adequately resourced and no double dipping)');
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(4,'Risks','active','1','How well are we doing resource utilization and/or budget management? <p>(1 - Significantly underutilizing/over the budget; 5 - 100% utilization and within budget)');
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(5,'Quality','active','1','Are there any near term risks in the project? Is there a plan to address them? <p>(1 - Major risks, mitigation yet to be planned; 5 - either no risks or all risks have been mitigated/resolved)');
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(6,'Scope','active','1','Were there any Quality issues with the deliverables in the last month?<p> (1 - major quality issues; 5 - none or negligibly minor issues)');
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(7,'Change Managment','active','1','Is there any risk to the project scope? Is it steady or undergoing continuous changes?<p> (1 - continuously changing/Proving difficult to Control; 5 - Scope is under control)');
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(8,'Issues','active','1','Is there a documented change management process that is being implemented for transitions? <p>(1 - No change management document present; 3 - Basic Change management documentation exists; 5 - Change management is sufficiently well documented)');
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(9,'Overall','active','1','Can you think of any issues not captured so far or do you foresee anything in the near to medium future? <p>(1 - issues present with high risk; 5 - no issues present or I don’t foresee any issue at this stage)');
insert into project_healthcard.component(id,component_name,status,question_type,component_question) values(91,'Mitigation Steps','active','2','');

//projects//
INSERT INTO project_healthcard.project (`id`, `end_date`, `logo`, `project_name`, `start_date`, `status`, `client_id`,description) VALUES (1, '2030-12-05', 's3', 'Valet Closet', '2022-12-25', 'active', '1','NA');
INSERT INTO project_healthcard.project (`id`, `end_date`, `logo`, `project_name`, `start_date`, `status`, `client_id`,description) VALUES (2, '2030-12-05', 's3', 'Krysp', '2022-12-25', 'active', '2','NA');

//project_component//
insert into project_healthcard.project_component(project_id,component_id) values('1','1');
insert into project_healthcard.project_component(project_id,component_id) values('1','2');
insert into project_healthcard.project_component(project_id,component_id) values('1','3');
insert into project_healthcard.project_component(project_id,component_id) values('1','4');
insert into project_healthcard.project_component(project_id,component_id) values('1','5');
insert into project_healthcard.project_component(project_id,component_id) values('1','6');
insert into project_healthcard.project_component(project_id,component_id) values('1','7');
insert into project_healthcard.project_component(project_id,component_id) values('1','8');
insert into project_healthcard.project_component(project_id,component_id) values('1','9');
insert into project_healthcard.project_component(project_id,component_id) values('1','91');
insert into project_healthcard.project_component(project_id,component_id) values('2','1');
insert into project_healthcard.project_component(project_id,component_id) values('2','2');
insert into project_healthcard.project_component(project_id,component_id) values('2','3');
insert into project_healthcard.project_component(project_id,component_id) values('2','4');
insert into project_healthcard.project_component(project_id,component_id) values('2','5');
insert into project_healthcard.project_component(project_id,component_id) values('2','6');
insert into project_healthcard.project_component(project_id,component_id) values('2','7');
insert into project_healthcard.project_component(project_id,component_id) values('2','8');
insert into project_healthcard.project_component(project_id,component_id) values('2','9');
insert into project_healthcard.project_component(project_id,component_id) values('2','91');



//project_employee//
insert into project_healthcard.project_employees(`id`,`role`,`employee_id`,`project_id`) values('4','DM','2','1');
insert into project_healthcard.project_employees(`id`,`role`,`employee_id`,`project_id`) values('6','DM','2','2');




