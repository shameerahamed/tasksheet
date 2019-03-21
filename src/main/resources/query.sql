select current_date from App.tbl_User


select * from APP.TBL_TASK where 
		DATE <= current_date and date >= ('2010-12-20')

select current_timestamp from APP.TBL_USER
		
select DATE({fn TIMESTAMPADD( SQL_TSI_MONTH, -1, CURRENT_TIMESTAMP)}) from APP.TBL_USER

values ({fn TIMESTAMPADD( SQL_TSI_DAY, 5, CURRENT_TIMESTAMP)});




select * from APP.TBL_TASK where 
		DATE <= current_date and date >= DATE({fn TIMESTAMPADD( SQL_TSI_MONTH, -1, CURRENT_TIMESTAMP)})
		
	
SELECT * FROM App.tbl_task 
    	WHERE user_id = 'shameer' 
    	and	date <= current_date 
    	and date >= DATE({fn TIMESTAMPADD( SQL_TSI_MONTH, -1, CURRENT_TIMESTAMP)})
    	ORDER BY date DESC
    	
    	
    	