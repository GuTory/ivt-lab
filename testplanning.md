| test scenarios                                            | test function                                                   |
|-----------------------------------------------------------|-----------------------------------------------------------------|
| both empty                                                | fireTorpedo_Empty_Failure()                                     |
| fire the first and it is unsuccessful                     | fireTorpedo_First_Empty_Success()                               |
| fire first fail and fire second successful                | fireTorpedo_Second_Empty_Success()                              |
| both fail                                                 | fireTorpedo_Fire_Fails()                                        |
| primary was fired last, secondary fails still fires first | fireTorpedo_PrimaryWasFiredLast_Secondary_Fails_First_Success() |
| primary was fired last, secondary and first fails         | fireTorpedo_PrimaryWasFiredLast_Secondary_Fails_First_fails()   |

