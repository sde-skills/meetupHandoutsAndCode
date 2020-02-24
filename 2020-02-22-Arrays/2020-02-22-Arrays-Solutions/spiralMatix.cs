public class Solution
    {
        enum Direction { Left = 0, Down = 1, Right = 2, Up = 3 };

        public IList<int> SpiralOrder(int[][] matrix)
        {
            var output = new List<int>();

            int m = matrix.Length;

            if (m > 0)
            {
                int n = matrix[0].Length;

                var top = 0;
                var bot = m - 1;
                var left = 0;
                var right = n - 1;

                Direction direction = Direction.Left;

                while (top <= bot && left <= right)
                {
                    if (direction == Direction.Left)
                    {
                        for (var i = left; i <= right; i++)
                            output.Add(matrix[top][i]);
                        top++;
                    }

                    else if (direction == Direction.Down)
                    {
                        for (var i = top; i <= bot; i++)
                            output.Add(matrix[i][right]);
                        right--;
                    }

                    else if (direction == Direction.Right)
                    {
                        for (var i = right; i >= left; i--)
                            output.Add(matrix[bot][i]);

                        bot--;
                    }

                    else if (direction == Direction.Up)
                    {
                        for (var i = bot; i >= top; i--)
                            output.Add(matrix[i][left]);

                        left++;
                    }

                    direction++;
                    if (direction > Direction.Up)
                        direction = Direction.Left;
                }


            }

            return output;
        }
    }
