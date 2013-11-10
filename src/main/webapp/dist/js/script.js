var breakfast = [
"1 Pancake (116 g)", 
"1 Pancake (116 g) w/ 60 mL Maple Syrup", 
"1 Pancake (116 g) w/ 4 g Butter", 
"1 Waffle (33 g)", 
"1 Waffle (33 g) w/ 60 mL Maple Syrup", 
"1 Waffle (33 g) w/ 4 g Butter", 
"Donut (Glazed, Standard)", 
"1 Slice Toast (50 g)", 
"1 Slice Toast (50 g) w/ Butter and Jam", 
"Butter (4 g or 0.8 teaspoons)", 
"1 Packet Strawberry Preserves", 
"1 Croissant ", 
"1 Croissant w/ Nutela (2 tablespoons)", 
"Nutela (2 tablespoons)", 
"1 Bagel (75 g) w/ Cream Cheese", 
"1 Bagel (75 g)", 
"Cream Cheese (30 g)", 
"Orange Juice (1 cup)", 
"Whole Milk (1 cup)", 
"Skim Milk (1 cup)", 
"Oatmeal (1 cup)", 
"Frosted Flakes (1 cup)", 
"Lucky Charms (1 cup)", 
"Special K (1 cup)", 
"Honey Nut Cheerios (1 cup)", 
"Cocoa Puffs (1 cup)", 
"Cinnamon Toast Crunch (1 cup)", 
"1 Egg Cheese Omelette", 
"1 Egg Scrambled", 
"1 Egg Fried", 
"Apple (182 g)", 
"Banana (118 g)", 
"Mixed Fruit (0.5 cup)", 
"1 Cup Plain Yogurt"
];

var other = [
"0.25 Cups Cheese (Shredded) - Cheddar", 
"1 Slice Cheese - Swiss", 
"1 Slice Toast (50 g)", 
"Grilled Cheese Sandwitch (2 slices toast)", 
"1 Hot Dog", 
"1 Hot Dog Bun", 
"1 Hot Dog w/ Ketchup", 
"1 Hot Dog w/o Ketchup", 
"1 tbspn of Ketchup", 
"1slice from a Large Cheese Pizza", 
"1 slice from a Large Pepperoni Pizza", 
"4 slices of Ham", 
"1 Fried Chicken", 
"Buffalo Wings (3 oz.)", 
"French Fries (134 g)", 
"Hamburger", 
"Cheese Burger", 
"Mashed Potatoes (1 cup)", 
"Caesar Salad (1/2 package)", 
"Caesar Salad w/ Ranch Dressing", 
"Ranch Dressing (1 tbspoon)", 
"Roasted Vegetables (140 g)", 
"Fried Rice (1 cup)", 
"White Rice (1 cup)", 
"Orange Juice (1 cup)", 
"Apple Juice (1 cup)", 
"Steak (182 g )"
];

$(window).load(function() {
	$("#break").autocomplete({
		source: breakfast
	});
	$("#lunch").autocomplete({
		source: other
	});
	$("#dinner").autocomplete({
		source: other
	});
	$("#submit").click(function() {
		var value1 = breakfast.indexOf($("#break").val());
		$("#break").val(value1);
		var value2 = other.indexOf($("#lunch").val());
		$("#lunch").val(value2+34);
		var value3 = other.indexOf($("#dinner").val());
		$("#dinner").val(value3+34);
	})
});

