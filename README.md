# Bandaid.AI – AI-Powered First Aid & Safety Assistant
“In an emergency, calm and clear guidance saves lives.”

Bandaid.AI helps people handle medical emergencies intelligently and calmly.
Using AWS AI services, MongoDB, and SageMaker, it analyzes a user’s text or image input, 
detects key symptoms or injury types, and gives real-time, step-by-step first aid advice 
— along with nearby hospitals, friend alerts, and context-aware recommendations.

## Inspiration
The idea came from a real incident:
When Ryan(our friend) was bitten by a neighbor’s dog, we scrambled to find what to do — searching Google, 
calling the clinic, and messaging friends — all while he was bleeding.
That panic made us wonder: Why isn’t there a smart assistant that guides you through emergencies in seconds?
So we built Bandaid — an AI safety companion that can turn chaos into calm.

## How it works
1.Understand injury descriptions
Users can type or speak:
“I got bitten by a small dog and it’s bleeding a bit.”
The AI automatically extracts key terms (“dog bite”, “bleeding”) and classifies injury type & severity.
2.Visual recognition (optional)
Upload a wound photo → AidMate uses SageMaker and Rekognition to estimate wound severity.
3.Step-by-step instructions
The AI generates immediate actions (e.g., “wash with soap”, “apply iodine”, “get a rabies shot within 24h”).
[Future Update Plans]
4.Location-based care
Finds the nearest open clinics or urgent care centers using Amazon Location Service.
5.Emergency sharing
Sends an automated text with location & injury details to emergency contacts via AWS SNS.
6.Personalization
Takes into account stored health info (allergies, vaccines, medications) from MongoDB.

## References
DataSet: 
uwm-bigdata/wound-classification-using-images-and-locations --- uwm-bigdata/wound-classification-using-images-and-locations
AZH dataset is collected over a two-year clinical period at the AZH Wound and Vascular Center in Milwaukee, Wisconsin. These images contain four different wound types: venous, diabetic, pressure, and surgical. It includes the location of the wound, index(severity of the wound, 0-5), and the corresponding image.

Special Thanks to AWS, MongoDB, DubHacks2025
