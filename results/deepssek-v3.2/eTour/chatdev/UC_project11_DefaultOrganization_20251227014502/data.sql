/**
 * Sample data for Convention History database.
 * This ensures the application has data to display immediately.
 */
-- Clear existing data to avoid duplicates
DELETE FROM conventions;
-- Insert sample convention data
INSERT INTO conventions (name, point_of_rest, date, description) VALUES 
('Annual Food Expo 2024', 'The Gourmet Hub', '2024-01-15', 'Largest annual food industry convention featuring 500+ exhibitors showcasing latest food trends and innovations'),
('International Chefs Conference', 'The Gourmet Hub', '2024-02-20', 'Global gathering of Michelin-star chefs sharing techniques and discussing culinary future with AI integration'),
('Wine & Dine Festival', 'Vineyard Restaurant', '2024-03-10', 'Premium wine tasting event featuring 100+ international wineries paired with gourmet dishes'),
('Hospitality Leadership Summit', 'Grand Hotel Dining', '2024-01-30', 'Exclusive summit for hotel executives discussing post-pandemic recovery and digital transformation'),
('Future of Food Tech', 'The Gourmet Hub', '2024-04-05', 'Cutting-edge conference exploring AI, robotics, lab-grown meats, and sustainability in culinary industry'),
('Sustainable Restaurant Conference', 'Green Bistro', '2024-05-12', 'Focus on eco-friendly pract, zero-waste cooking, and carbon-neutral restaurant operations'),
('Asian Fusion Culinary Workshop', 'Dragon Palace', '2024-06-08', 'Hands-on training in modern Asian fusion techniques by award-winning chefs from across Asia'),
('Artisan Bread Symposium', 'Boulangerie Central', '2024-07-19', 'Traditional and innovative bread-making methods, sourdough science, and ancient grain revival'),
('Coffee Masters Convention', 'Bean There Cafe', '2024-08-25', 'World barista championship combined with coffee science seminars and sustainable sourcing discussions'),
('Seafood Sustainability Forum', 'Ocean''s Catch', '2024-09-14', 'Marine conservation experts and chefs collaborate on sustainable fishing pract and ocean preservation');