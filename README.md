# gc-labs
## Lab exercises for gc

Lab exercises for use in GC training course. `gc-issues.jar` file will run
the various scenarios for investigation. Execute the exercises as follows:

```
java -jar gc-issues.jar <IPofNodeToConnectTo> <Lab_Number>
```

# Exercise numbers:

	1 Tombstone Hell - Generate lots of rows and then delete majority of columns
	2 Batch Slapped  - Generate large batch inserts to overload the server
	3 Kevin Smithed  - Create excessively wide rows in Cassandra that woudl require buying 
		* second seat 
		* seat belt extender

