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
});

