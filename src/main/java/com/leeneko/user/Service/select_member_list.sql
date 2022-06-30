SELECT 
	A.FACTORY_CODE, 
	A.USER_ID, 
	A.USER_DESC
FROM "MEMBER" AS A 
WHERE A.FACTORY_CODE = :factoryCode
#if($userId)
AND A.USER_ID = :userId
#end
#if($fromDate)
AND TO_DATE(SUBSTRING(A.user_id, 1, 6), 'YYYYMM') >= TO_DATE(:fromDate, 'YYYYMMDD')
#end
#if($toDate)
AND TO_DATE(SUBSTRING(A.user_id, 1, 6), 'YYYYMM') <= TO_DATE(:toDate, 'YYYYMMDD')
#end