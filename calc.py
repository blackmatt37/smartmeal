
def calcProtein(weight, activity): # grams
	if activity == 0:
		return weight*(2.0/5)
	if activity == 1:
		return weight*(1.0/2)
	if activity == 2:
		return weight*(3.0/5)
	if activity == 3:
		return weight*(7.0/10)

def calcCalories(age, gender, activity, height, weight):
	prelim = 0
	if gender == 'male':
		prelim =  66.47 + 6.25*weight + 12.7*height - 6.76*age
		if activity == 0:
			return prelim*1.3
		if activity == 1:
			return prelim*1.6
		if activity == 2:
			return prelim*1.8
		else:
			return prelim*2.2
	elif gender == 'female':
		prelim = 65.51 + 4.386*weight + 4.6736*height - 4.68*age
		if activity == 0:
			return prelim*1.3
		if activity == 1:
			return prelim*1.5
		if activity == 2:
			return prelim*1.7
		else:
			return prelim*1.9

def calcInches(feet, inches):
	return feet*12 + inches

def calcCalcium(age, gender): #milligrams
	if age < 1:
		return 200
	if 1 < age < 3:
		return 700
	if 4 < age < 8:
		return 1000
	if 9 < age < 18:
		return 1300
	if 19 < age < 50:
		return 1000
	if 51 < age < 70:
		if gender == 'male':
			return 1000
		else:
			return 1200
	else:
		return 1200

print calcCalcium(18, )

# print calcCalories(17, 'male', 3, calcInches(5,8), 120)
# print calcProtein(200, 1)
