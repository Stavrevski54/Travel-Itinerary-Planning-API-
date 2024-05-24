-- Truncate tables to remove existing data
TRUNCATE TABLE activity, booking, itinerary, destination, account RESTART IDENTITY CASCADE;

-- Insert accounts
INSERT INTO account (id, username, password, email) VALUES
                                                        (1, 'mykonos_user', 'password123', 'mykonos_user@example.com'),
                                                        (2, 'kopaonik_user', 'password456', 'kopaonik_user@example.com');

-- Insert destinations
INSERT INTO destination (id, name, description, location, best_time_to_visit) VALUES
                                                                                  (1, 'Mykonos', 'Beautiful Greek island with stunning beaches and vibrant nightlife.', 'Greece', 'June to September'),
                                                                                  (2, 'Kopaonik', 'Serbia''s largest mountain range, known for its ski resorts and hiking trails.', 'Serbia', 'December to March for skiing, June to September for hiking');

-- Insert itineraries
INSERT INTO itinerary (id, name, start_date, end_date, user_id, destination_id) VALUES
                                                                                    (1, 'Mykonos Summer Vacation', '2024-07-01', '2024-07-07', 1, 1),
                                                                                    (2, 'Kopaonik Winter Getaway', '2024-12-15', '2024-12-20', 2, 2);

-- Insert bookings
INSERT INTO booking (id, type, details, booking_date, itinerary_id) VALUES
                                                                        (1, 'Flight', 'Flight to Mykonos', '2024-07-01', 1),
                                                                        (2, 'Hotel', 'Hotel booking in Mykonos', '2024-07-02', 1),
                                                                        (3, 'Flight', 'Flight to Kopaonik', '2024-12-15', 2),
                                                                        (4, 'Hotel', 'Hotel booking in Kopaonik', '2024-12-16', 2);

-- Insert activities
INSERT INTO activity (id, name, description, date, itinerary_id) VALUES
                                                                     (1, 'Beach Party', 'A fun beach party in Mykonos with live DJ and cocktails.', '2024-07-03', 1),
                                                                     (2, 'Island Tour', 'A guided tour around the island of Mykonos.', '2024-07-04', 1),
                                                                     (3, 'Skiing', 'A full day of skiing in Kopaonik.', '2024-12-17', 2),
                                                                     (4, 'Hiking', 'A hiking trip in Kopaonik''s beautiful mountain trails.', '2024-12-18', 2);

-- Reset the ID sequence to ensure new entries start from the next available ID
SELECT setval(pg_get_serial_sequence('account', 'id'), coalesce(max(id), 1)) FROM account;
SELECT setval(pg_get_serial_sequence('destination', 'id'), coalesce(max(id), 1)) FROM destination;
SELECT setval(pg_get_serial_sequence('itinerary', 'id'), coalesce(max(id), 1)) FROM itinerary;
SELECT setval(pg_get_serial_sequence('booking', 'id'), coalesce(max(id), 1)) FROM booking;
SELECT setval(pg_get_serial_sequence('activity', 'id'), coalesce(max(id), 1)) FROM activity;
