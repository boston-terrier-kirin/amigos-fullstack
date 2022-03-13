import fetch from 'unfetch';

const checkStatus = (res) => {
  if (res.ok) {
    return res;
  }

  console.log('🍜', res);

  const err = new Error(res.statusText);
  err.response = res;
  return Promise.reject(err);
};

export const getAllStudents = () => fetch('/api/v1/students').then(checkStatus);

export const addNewStudent = (student) => {
  return fetch('api/v1/students', {
    headers: {
      'Content-Type': 'application/json',
    },
    method: 'POST',
    body: JSON.stringify(student),
  }).then(checkStatus);
};

export const deleteStudent = (studentId) =>
  fetch(`api/v1/students/${studentId}`, {
    method: 'DELETE',
  }).then(checkStatus);
