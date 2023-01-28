# VK-Signature-Generator
Generates signature for VK API urls

Собираем запрос из нескольких частей (пример). 
	- метод getApiHost() возвращает https://clientapi.mail.ru/
	- метод getApiPath() возвращает fcgi-bin/pushstatus (где fcgi-bin путь API, pushstatus метод который будет в строке)
	- тело запроса с параметрами
	
Итоговый запрос строится по такой формуле:
	getApiHost() + getApiPath() + параметры
	
Учтите что сигнатура генерируется автоматически и ее не должно быть в запросе в качестве параметра

Параметры состоят из сортированного по алфавиту списка ключей и значений. Разделены символом &
Пример: ключ=значение&ключ=значение...

Этапы генерации:
	1. Получаем список параметров из запроса
	2. Проходим по сортированному списку параметров и собираем одну строку
	3. Конвертируем applicationKey в HEX
	4. Собираем строку для генерации. Название метода + параметры + HEX значение applicationKey
	5. Полученную строку хешируем ахгоритмом MD5
	
Сгенерировання сигнатура: 685c1f02a9a893eaf9bf086a49bf0b39

Используя сгенерированную сигнатуру собираем итоговый запрос. 

Assembling a query from several parts (example).
- method getApiHost() returns https://clientapi.mail.ru/
- getApiPath() method returns fcgi-bin/pushstatus (where fcgi-bin is the API path, pushstatus method will be in the string)
- request body with parameters

The final query is built according to the following formula:
getApiHost() + getApiPath() + parameters

Please note that the signature is generated automatically and should not be in the request as a parameter

The parameters consist of an alphabetically sorted list of keys and values. Separated by &
Example: key=value&key=value...

Generation steps:
1. Get the list of parameters from the request
2. We pass through the sorted list of parameters and collect one line
3. Convert applicationKey to HEX
4. We collect the string for generation. Method name + parameters + HEX value of applicationKey
5. The resulting string is hashed using the MD5 algorithm

Generated signature: 685c1f02a9a893eaf9bf086a49bf0b39

Using the generated signature, we collect the final request.
