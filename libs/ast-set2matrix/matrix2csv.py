import os
import json

from lib.helpers.TimeLogger import TimeLogger


def matrix2csv(input_file, output_file):
    time_logger = TimeLogger(task_name='Matrix to CSV transformation')

    with open(input_file, 'r') as matrix_file_descriptor:
        matrix_json = matrix_file_descriptor.read()
        matrix = json.loads(matrix_json)

    vectors = []

    for i, vector in enumerate(matrix):
        time_logger_file = TimeLogger(task_name='%d-th vector processed' % i)
        vectors.append(','.join(str(x) for x in vector))
        time_logger_file.finish()

    csv = os.linesep.join(vectors)

    with open(output_file, 'w') as csv_file_descriptor:
        csv_file_descriptor.write(csv)

    time_logger.finish(full_finish=True)
