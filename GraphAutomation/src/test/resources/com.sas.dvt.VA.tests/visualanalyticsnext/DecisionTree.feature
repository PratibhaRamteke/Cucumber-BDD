Feature: Decision Tree Plot Tests
Description: The purpose of this feature is to test all features/options of time series plot

@van0101i
Scenario: Draw basic DecisionTree plot
	Given Login to VAN
	When Select "CARS" dataset
	And Drag and drop "Decision Tree" at "TOPLEFT"